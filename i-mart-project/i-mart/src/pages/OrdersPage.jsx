import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const OrdersPage = () => {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    const storedOrders = JSON.parse(localStorage.getItem('orders') || '[]');
    setOrders(storedOrders.reverse()); // Show latest first
  }, []);

  const getStatusColor = (status) => {
    switch (status) {
      case 'Processing':
        return 'bg-blue-100 text-blue-800';
      case 'Shipped':
        return 'bg-yellow-100 text-yellow-800';
      case 'Delivered':
        return 'bg-green-100 text-green-800';
      case 'Cancelled':
        return 'bg-red-100 text-red-800';
      default:
        return 'bg-gray-100 text-gray-800';
    }
  };

  if (orders.length === 0) {
    return (
      <div className="min-h-screen bg-light py-16">
        <div className="container-custom text-center">
          <div className="text-6xl mb-4">📦</div>
          <h1 className="text-3xl font-bold mb-4">No Orders Yet</h1>
          <p className="text-gray-600 mb-8">
            You haven't placed any orders yet. Start shopping to see your orders here!
          </p>
          <Link to="/products" className="btn-primary">
            Start Shopping
          </Link>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-light py-8">
      <div className="container-custom">
        <h1 className="text-3xl font-bold mb-8">My Orders</h1>

        <div className="space-y-6">
          {orders.map((order) => (
            <div key={order.id} className="card p-6 fade-in">
              {/* Order Header */}
              <div className="flex flex-col md:flex-row md:items-center justify-between mb-6 pb-4 border-b">
                <div>
                  <h3 className="text-xl font-bold mb-2">Order #{order.id}</h3>
                  <p className="text-sm text-gray-600">
                    Placed on {new Date(order.date).toLocaleDateString()} at{' '}
                    {new Date(order.date).toLocaleTimeString()}
                  </p>
                </div>
                <div className="mt-4 md:mt-0">
                  <span
                    className={`px-4 py-2 rounded-full text-sm font-semibold ${getStatusColor(
                      order.status
                    )}`}
                  >
                    {order.status}
                  </span>
                </div>
              </div>

              {/* Order Items */}
              <div className="space-y-4 mb-6">
                {order.items.map((item) => (
                  <div key={item.id} className="flex gap-4">
                    <img
                      src={item.image}
                      alt={item.name}
                      className="w-20 h-20 object-cover rounded-lg"
                    />
                    <div className="flex-1">
                      <h4 className="font-semibold mb-1">{item.name}</h4>
                      <p className="text-sm text-gray-600 mb-1">
                        Quantity: {item.quantity}
                      </p>
                      <p className="font-bold">₹{item.price * item.quantity}</p>
                    </div>
                  </div>
                ))}
              </div>

              {/* Order Footer */}
              <div className="flex flex-col md:flex-row md:items-center justify-between pt-4 border-t">
                <div>
                  <p className="text-sm text-gray-600 mb-1">Total Amount</p>
                  <p className="text-2xl font-bold text-primary">
                    ₹{order.total.toFixed(0)}
                  </p>
                  <p className="text-sm text-gray-600 mt-1 capitalize">
                    Payment: {order.paymentMethod}
                  </p>
                </div>
                <div className="mt-4 md:mt-0 flex gap-3">
                  <Link
                    to={`/order/${order.id}`}
                    className="btn-outline"
                  >
                    View Details
                  </Link>
                  {order.status === 'Delivered' && (
                    <button className="btn-primary">
                      Write Review
                    </button>
                  )}
                </div>
              </div>

              {/* Delivery Address */}
              <div className="mt-4 p-4 bg-light rounded-lg">
                <p className="text-sm font-semibold mb-2">Delivery Address:</p>
                <p className="text-sm text-gray-700">
                  {order.shippingAddress.fullName}, {order.shippingAddress.address},{' '}
                  {order.shippingAddress.city}, {order.shippingAddress.state} -{' '}
                  {order.shippingAddress.pincode}
                </p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default OrdersPage;
