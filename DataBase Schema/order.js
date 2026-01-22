import React, { useState } from "react";
import ProductList from "./ProductList";
import Cart from "./Cart";
import products from "./products";

function App() {
  const [cart, setCart] = useState([]);

  // Add to Cart
  const addToCart = (product) => {
    const existing = cart.find(item => item.id === product.id);

    if (existing) {
      setCart(cart.map(item =>
        item.id === product.id
          ? { ...item, qty: item.qty + 1 }
          : item
      ));
    } else {
      setCart([...cart, { ...product, qty: 1 }]);
    }
  };

  return (
    <div>
      <h2>Flipkart Add to Cart</h2>
      <Cart cart={cart} />
      <ProductList products={products} addToCart={addToCart} />
    </div>
  );
}

export default App;
