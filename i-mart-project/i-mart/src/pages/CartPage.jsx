import { Link, useNavigate } from 'react-router-dom';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';

const CartPage = () => {
  const { cartItems, updateQuantity, removeFromCart, getCartTotal } = useCart();
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();

  const handleCheckout = () => {
    if (!isAuthenticated) {
      navigate('/login');
    } else {
      navigate('/checkout');
    }
  };

  if (cartItems.length === 0) {
    return (
      <div className="min-h-screen bg-light py-16">
        <div className="container-custom text-center">
          <div className="text-6xl mb-4">🛒</div>
          <h1 className="text-3xl font-bold mb-4">Your Cart is Empty</h1>
          <p className="text-gray-600 mb-8">Add some products to get started!</p>
          <Link to="/products" className="btn-primary">
            Continue Shopping
          </Link>
        </div>
      </div>
    );
  }

  const subtotal = getCartTotal();
  const discount = subtotal * 0.05; // 5% discount
  const deliveryCharges = subtotal > 499 ? 0 : 50;
  const total = subtotal - discount + deliveryCharges;

  return (
    <div className="min-h-screen bg-light py-8">
      <div className="container-custom">
        <h1 className="text-3xl font-bold mb-8">Shopping Cart</h1>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          {/* Cart Items */}
          <div className="lg:col-span-2 space-y-4">
            {cartItems.map((item) => (
              <div key={item.id} className="card p-6 fade-in">
                <div className="flex flex-col sm:flex-row gap-4">
                  {/* Image */}
                  <Link to={`/product/${item.id}`} className="flex-shrink-0">
                    <img
                      src={item.image}
                      alt={item.name}
                      className="w-full sm:w-32 h-32 object-cover rounded-lg"
                    />
                  </Link>

                  {/* Details */}
                  <div className="flex-1">
                    <Link to={`/product/${item.id}`}>
                      <h3 className="font-bold text-lg mb-2 hover:text-primary transition-colors">
                        {item.name}
                      </h3>
                    </Link>
                    <p className="text-sm text-gray-600 mb-2">{item.brand}</p>
                    <div className="flex items-center gap-2 mb-3">
                      <span className="text-2xl font-bold">₹{item.price}</span>
                      {item.originalPrice > item.price && (
                        <>
                          <span className="text-sm text-gray-500 line-through">
                            ₹{item.originalPrice}
                          </span>
                          <span className="text-sm text-green-600 font-semibold">
                            {item.discount}% off
                          </span>
                        </>
                      )}
                    </div>

                    {/* Quantity Controls */}
                    <div className="flex items-center gap-4">
                      <div className="flex items-center gap-2">
                        <button
                          onClick={() => updateQuantity(item.id, item.quantity - 1)}
                          className="w-8 h-8 border-2 border-gray-300 rounded hover:border-primary transition-colors font-bold"
                        >
                          -
                        </button>
                        <span className="w-12 text-center font-bold">{item.quantity}</span>
                        <button
                          onClick={() => updateQuantity(item.id, item.quantity + 1)}
                          className="w-8 h-8 border-2 border-gray-300 rounded hover:border-primary transition-colors font-bold"
                        >
                          +
                        </button>
                      </div>
                      <button
                        onClick={() => removeFromCart(item.id)}
                        className="text-red-600 hover:text-red-700 font-semibold text-sm"
                      >
                        Remove
                      </button>
                    </div>
                  </div>

                  {/* Item Total */}
                  <div className="text-right">
                    <p className="text-sm text-gray-600 mb-1">Item Total</p>
                    <p className="text-2xl font-bold">
                      ₹{(item.price * item.quantity).toLocaleString()}
                    </p>
                  </div>
                </div>
              </div>
            ))}
          </div>

          {/* Order Summary */}
          <div className="lg:col-span-1">
            <div className="card p-6 sticky top-24">
              <h2 className="text-xl font-bold mb-6">Order Summary</h2>

              <div className="space-y-4 mb-6">
                <div className="flex justify-between">
                  <span className="text-gray-600">Subtotal</span>
                  <span className="font-semibold">₹{subtotal.toLocaleString()}</span>
                </div>
                <div className="flex justify-between text-green-600">
                  <span>Discount (5%)</span>
                  <span className="font-semibold">-₹{discount.toFixed(0)}</span>
                </div>
                <div className="flex justify-between">
                  <span className="text-gray-600">Delivery Charges</span>
                  <span className="font-semibold">
                    {deliveryCharges === 0 ? (
                      <span className="text-green-600">FREE</span>
                    ) : (
                      `₹${deliveryCharges}`
                    )}
                  </span>
                </div>
                <div className="border-t pt-4 flex justify-between text-lg">
                  <span className="font-bold">Total</span>
                  <span className="font-bold text-primary">₹{total.toFixed(0)}</span>
                </div>
              </div>

              {subtotal < 499 && (
                <div className="bg-blue-50 border border-blue-200 rounded-lg p-4 mb-6">
                  <p className="text-sm text-blue-800">
                    Add items worth ₹{(499 - subtotal).toFixed(0)} more to get FREE delivery!
                  </p>
                </div>
              )}

              <button onClick={handleCheckout} className="btn-primary w-full mb-4">
                Proceed to Checkout
              </button>

              <Link to="/products" className="btn-outline w-full block text-center">
                Continue Shopping
              </Link>

              {/* Benefits */}
              <div className="mt-6 space-y-3 text-sm">
                <div className="flex items-center gap-2">
                  <span className="text-green-600">✓</span>
                  <span>Safe and Secure Payments</span>
                </div>
                <div className="flex items-center gap-2">
                  <span className="text-green-600">✓</span>
                  <span>Easy Returns & Refunds</span>
                </div>
                <div className="flex items-center gap-2">
                  <span className="text-green-600">✓</span>
                  <span>100% Authentic Products</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CartPage;
