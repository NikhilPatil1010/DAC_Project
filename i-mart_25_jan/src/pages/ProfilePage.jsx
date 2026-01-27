import { useState } from 'react';
import { useAuth } from '../context/AuthContext';

const ProfilePage = () => {
  const { user, updateProfile } = useAuth();
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState({
    name: user?.name || '',
    email: user?.email || '',
    phone: user?.phone || '',
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    updateProfile(formData);
    setIsEditing(false);
  };

  return (
    <div className="min-h-screen bg-light py-8">
      <div className="container-custom">
        <h1 className="text-3xl font-bold mb-8">My Profile</h1>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          {/* Sidebar */}
          <div className="lg:col-span-1">
            <div className="card p-6">
              <div className="text-center mb-6">
                <div className="w-24 h-24 bg-primary text-white rounded-full flex items-center justify-center text-4xl font-bold mx-auto mb-4">
                  {user?.name?.charAt(0).toUpperCase()}
                </div>
                <h2 className="text-xl font-bold">{user?.name}</h2>
                <p className="text-gray-600">{user?.email}</p>
              </div>

              <div className="space-y-2">
                <button className="w-full text-left px-4 py-3 rounded-lg bg-primary text-white font-semibold">
                  Profile Information
                </button>
                <button className="w-full text-left px-4 py-3 rounded-lg hover:bg-gray-100 transition-colors">
                  My Orders
                </button>
                <button className="w-full text-left px-4 py-3 rounded-lg hover:bg-gray-100 transition-colors">
                  Wishlist
                </button>
                <button className="w-full text-left px-4 py-3 rounded-lg hover:bg-gray-100 transition-colors">
                  Saved Addresses
                </button>
                <button className="w-full text-left px-4 py-3 rounded-lg hover:bg-gray-100 transition-colors">
                  Settings
                </button>
              </div>
            </div>
          </div>

          {/* Main Content */}
          <div className="lg:col-span-2">
            <div className="card p-6">
              <div className="flex items-center justify-between mb-6">
                <h2 className="text-2xl font-bold">Personal Information</h2>
                {!isEditing && (
                  <button
                    onClick={() => setIsEditing(true)}
                    className="text-primary hover:underline font-semibold"
                  >
                    Edit
                  </button>
                )}
              </div>

              {isEditing ? (
                <form onSubmit={handleSubmit} className="space-y-6">
                  <div>
                    <label className="block text-sm font-semibold mb-2">
                      Full Name
                    </label>
                    <input
                      type="text"
                      name="name"
                      value={formData.name}
                      onChange={handleChange}
                      className="input-field"
                    />
                  </div>

                  <div>
                    <label className="block text-sm font-semibold mb-2">
                      Email Address
                    </label>
                    <input
                      type="email"
                      name="email"
                      value={formData.email}
                      onChange={handleChange}
                      className="input-field"
                    />
                  </div>

                  <div>
                    <label className="block text-sm font-semibold mb-2">
                      Phone Number
                    </label>
                    <input
                      type="tel"
                      name="phone"
                      value={formData.phone}
                      onChange={handleChange}
                      className="input-field"
                    />
                  </div>

                  <div className="flex gap-4">
                    <button type="submit" className="btn-primary">
                      Save Changes
                    </button>
                    <button
                      type="button"
                      onClick={() => {
                        setIsEditing(false);
                        setFormData({
                          name: user?.name || '',
                          email: user?.email || '',
                          phone: user?.phone || '',
                        });
                      }}
                      className="btn-outline"
                    >
                      Cancel
                    </button>
                  </div>
                </form>
              ) : (
                <div className="space-y-6">
                  <div>
                    <p className="text-sm text-gray-600 mb-1">Full Name</p>
                    <p className="font-semibold text-lg">{user?.name}</p>
                  </div>

                  <div>
                    <p className="text-sm text-gray-600 mb-1">Email Address</p>
                    <p className="font-semibold text-lg">{user?.email}</p>
                  </div>

                  <div>
                    <p className="text-sm text-gray-600 mb-1">Phone Number</p>
                    <p className="font-semibold text-lg">{user?.phone}</p>
                  </div>

                  <div>
                    <p className="text-sm text-gray-600 mb-1">Member Since</p>
                    <p className="font-semibold text-lg">
                      {new Date(user?.createdAt).toLocaleDateString()}
                    </p>
                  </div>
                </div>
              )}
            </div>

            {/* Account Stats */}
            <div className="grid grid-cols-2 md:grid-cols-4 gap-4 mt-6">
              <div className="card p-4 text-center">
                <p className="text-3xl font-bold text-primary mb-1">
                  {JSON.parse(localStorage.getItem('orders') || '[]').length}
                </p>
                <p className="text-sm text-gray-600">Total Orders</p>
              </div>
              <div className="card p-4 text-center">
                <p className="text-3xl font-bold text-primary mb-1">0</p>
                <p className="text-sm text-gray-600">Wishlist Items</p>
              </div>
              <div className="card p-4 text-center">
                <p className="text-3xl font-bold text-primary mb-1">0</p>
                <p className="text-sm text-gray-600">Reviews</p>
              </div>
              <div className="card p-4 text-center">
                <p className="text-3xl font-bold text-primary mb-1">0</p>
                <p className="text-sm text-gray-600">Saved Addresses</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProfilePage;
