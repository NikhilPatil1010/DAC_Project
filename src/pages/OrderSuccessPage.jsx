import { useEffect } from "react";
import { Link, useLocation } from "react-router-dom";

const OrderSuccessPage = () => {
  const location = useLocation();
  const order = location.state?.order;

  useEffect(() => {
    // Scroll to top
    window.scrollTo(0, 0);
  }, []);

  if (!order) {
    return (
      <div className="min-h-screen bg-light py-16">
        <div className="container-custom text-center">
          <h1 className="text-3xl font-bold mb-4">Order not found</h1>
          <Link to="/" className="btn-primary">
            Go to Home
          </Link>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-light py-12">
      <div className="container-custom">
        <div className="max-w-2xl mx-auto">
          <div className="card p-8 text-center fade-in">
            {/* Success Icon */}
            <div className="text-6xl mb-6">✅</div>

            {/* Success Message */}
            <h1 className="text-3xl font-bold mb-4 text-green-600">
              Order Placed Successfully!
            </h1>
            <p className="text-gray-600 mb-8">
              Thank you for shopping with I-Mart. Your order has been received
              and is being processed.
            </p>

            {/* Order Details */}
            <div className="bg-light p-6 rounded-lg mb-8 text-left">
              <div className="grid grid-cols-2 gap-4">
                <div>
                  <p className="text-sm text-gray-600 mb-1">Order ID</p>
                  <p className="font-bold">#{order.id}</p>
                </div>
                <div>
                  <p className="text-sm text-gray-600 mb-1">Order Date</p>
                  <p className="font-bold">
                    {new Date(order.date).toLocaleDateString()}
                  </p>
                </div>
                <div>
                  <p className="text-sm text-gray-600 mb-1">Total Amount</p>
                  <p className="font-bold text-primary">
                    ₹{order.total.toFixed(0)}
                  </p>
                </div>
                <div>
                  <p className="text-sm text-gray-600 mb-1">Payment Method</p>
                  <p className="font-bold capitalize">{order.paymentMethod}</p>
                </div>
              </div>
            </div>

            {/* Delivery Info */}
            <div className="bg-blue-50 border border-blue-200 rounded-lg p-6 mb-8 text-left">
              <h3 className="font-bold mb-3">Delivery Address</h3>
              <p className="text-sm mb-1">{order.shippingAddress.fullName}</p>
              <p className="text-sm mb-1">{order.shippingAddress.address}</p>
              <p className="text-sm mb-1">
                {order.shippingAddress.city}, {order.shippingAddress.state} -{" "}
                {order.shippingAddress.pincode}
              </p>
              <p className="text-sm mb-1">
                Phone: {order.shippingAddress.phone}
              </p>
              <p className="text-sm">Email: {order.shippingAddress.email}</p>
            </div>

            {/* Expected Delivery */}
            <div className="bg-green-50 border border-green-200 rounded-lg p-4 mb-8">
              <p className="text-green-800 font-semibold">
                🚚 Expected Delivery:{" "}
                {new Date(
                  Date.now() + 5 * 24 * 60 * 60 * 1000
                ).toLocaleDateString()} 
              </p>
            </div>

            {/* Actions */}
            <div className="flex flex-col sm:flex-row gap-4">
              <Link to="/orders" className="btn-primary flex-1">
                View Orders
              </Link>
              <Link to="/products" className="btn-outline flex-1">
                Continue Shopping
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrderSuccessPage;
