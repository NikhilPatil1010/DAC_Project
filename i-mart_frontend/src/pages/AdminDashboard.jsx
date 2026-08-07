import React, { useEffect, useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { 
  UsersIcon, 
  ShoppingBagIcon, 
  CurrencyDollarIcon, 
  ArchiveBoxIcon, 
  ExclamationTriangleIcon, 
  ClockIcon 
} from '@heroicons/react/24/outline';

const AdminDashboard = () => {
  const { user } = useAuth();
  const [analytics, setAnalytics] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchAnalytics = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await fetch('http://localhost:8081/api/admin/analytics', {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        
        if (!response.ok) {
          throw new Error('Failed to fetch analytics');
        }
        
        const data = await response.json();
        setAnalytics(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchAnalytics();
  }, []);

  if (loading) {
    return (
      <div className="flex justify-center items-center h-screen">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-orange-600"></div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="max-w-7xl mx-auto px-4 py-8">
        <div className="bg-red-50 text-red-600 p-4 rounded-lg flex items-center shadow">
          <ExclamationTriangleIcon className="h-6 w-6 mr-2" />
          <p>{error}</p>
        </div>
      </div>
    );
  }

  const statCards = [
    { name: 'Total Revenue', value: `₹${analytics?.totalRevenue?.toFixed(2) || '0.00'}`, icon: CurrencyDollarIcon, color: 'text-green-600', bg: 'bg-green-100' },
    { name: 'Total Orders', value: analytics?.totalOrders || 0, icon: ShoppingBagIcon, color: 'text-blue-600', bg: 'bg-blue-100' },
    { name: 'Pending Orders', value: analytics?.pendingOrders || 0, icon: ClockIcon, color: 'text-yellow-600', bg: 'bg-yellow-100' },
    { name: 'Total Products', value: analytics?.totalProducts || 0, icon: ArchiveBoxIcon, color: 'text-purple-600', bg: 'bg-purple-100' },
    { name: 'Low Stock Items', value: analytics?.lowStockProducts || 0, icon: ExclamationTriangleIcon, color: 'text-red-600', bg: 'bg-red-100' },
    { name: 'Registered Users', value: analytics?.totalUsers || 0, icon: UsersIcon, color: 'text-indigo-600', bg: 'bg-indigo-100' },
  ];

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-gray-900 tracking-tight">Admin Dashboard</h1>
          <p className="mt-2 text-sm text-gray-600">
            Welcome back, <span className="font-semibold text-orange-600">{user?.name}</span>. Here's what's happening with your store today.
          </p>
        </div>

        {/* Stats Grid */}
        <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
          {statCards.map((stat, index) => (
            <div 
              key={index} 
              className="bg-white overflow-hidden shadow-sm rounded-xl border border-gray-100 hover:shadow-md transition-shadow duration-300"
            >
              <div className="p-5">
                <div className="flex items-center">
                  <div className={`flex-shrink-0 rounded-lg p-3 ${stat.bg}`}>
                    <stat.icon className={`h-6 w-6 ${stat.color}`} aria-hidden="true" />
                  </div>
                  <div className="ml-5 w-0 flex-1">
                    <dl>
                      <dt className="text-sm font-medium text-gray-500 truncate">{stat.name}</dt>
                      <dd className="text-2xl font-bold text-gray-900">{stat.value}</dd>
                    </dl>
                  </div>
                </div>
              </div>
              <div className="bg-gray-50 px-5 py-3 border-t border-gray-100">
                <div className="text-sm">
                  <a href="#" className="font-medium text-orange-600 hover:text-orange-500 transition-colors">
                    View all<span className="sr-only"> {stat.name} stats</span>
                  </a>
                </div>
              </div>
            </div>
          ))}
        </div>

      </div>
    </div>
  );
};

export default AdminDashboard;
