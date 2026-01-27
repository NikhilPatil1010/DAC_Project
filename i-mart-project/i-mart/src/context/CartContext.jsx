import { createContext, useContext, useState, useEffect } from 'react';

const CartContext = createContext();

export const useCart = () => {
  const context = useContext(CartContext);
  if (!context) {
    throw new Error('useCart must be used within CartProvider');
  }
  return context;
};

export const CartProvider = ({ children, userId }) => {
  const [cartItems, setCartItems] = useState([]);
  const backendUrl = 'http://localhost:8080/api/cart'; // base URL

  // Load cart from backend on mount
  useEffect(() => {
    if (!userId) return;

    const fetchCart = async () => {
      try {
        const response = await fetch(`${backendUrl}?userId=${userId}`);
        if (!response.ok) throw new Error('Failed to fetch cart');
        const data = await response.json();
        setCartItems(data.items || []);
      } catch (err) {
        console.error(err);
      }
    };

    fetchCart();
  }, [userId]);

  const addToCart = async (product, quantity = 1) => {
    try {
      const response = await fetch(`${backendUrl}/items?userId=${userId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ productId: product.id, quantity }),
      });
      if (!response.ok) throw new Error('Failed to add item to cart');
      const data = await response.json();
      setCartItems(data.items || []);
    } catch (err) {
      console.error(err);
    }
  };

  const removeFromCart = async (productId) => {
    try {
      const response = await fetch(`${backendUrl}/items/${productId}?userId=${userId}`, {
        method: 'DELETE',
      });
      if (!response.ok) throw new Error('Failed to remove item');
      const data = await response.json();
      setCartItems(data.items || []);
    } catch (err) {
      console.error(err);
    }
  };

  const updateQuantity = async (productId, quantity) => {
    if (quantity <= 0) return removeFromCart(productId);

    try {
      const response = await fetch(`${backendUrl}/items/${productId}?userId=${userId}&quantity=${quantity}`, {
        method: 'PUT',
      });
      if (!response.ok) throw new Error('Failed to update quantity');
      const data = await response.json();
      setCartItems(data.items || []);
    } catch (err) {
      console.error(err);
    }
  };

  const clearCart = async () => {
    try {
      const response = await fetch(`${backendUrl}?userId=${userId}`, {
        method: 'DELETE',
      });
      if (!response.ok) throw new Error('Failed to clear cart');
      setCartItems([]);
    } catch (err) {
      console.error(err);
    }
  };

  const getCartTotal = () => {
    return cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
  };

  const getCartCount = () => {
    return cartItems.reduce((count, item) => count + item.quantity, 0);
  };

  const value = {
    cartItems,
    addToCart,
    removeFromCart,
    updateQuantity,
    clearCart,
    getCartTotal,
    getCartCount,
  };

  return <CartContext.Provider value={value}>{children}</CartContext.Provider>;
};
