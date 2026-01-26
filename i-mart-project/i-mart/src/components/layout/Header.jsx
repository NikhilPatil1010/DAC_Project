import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import { useCart } from '../../context/CartContext';

const Header = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [showMobileMenu, setShowMobileMenu] = useState(false);
  const { isAuthenticated, user, logout } = useAuth();
  const { getCartCount } = useCart();
  const navigate = useNavigate();

  const handleSearch = (e) => {
    e.preventDefault();
    if (searchQuery.trim()) {
      navigate(`/products?search=${searchQuery}`);
      setSearchQuery('');
    }
  };

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <header className="bg-white shadow-md sticky top-0 z-50">
      {/* Top Bar */}
      <div className="bg-secondary text-white py-2">
        <div className="container-custom flex justify-between items-center text-sm">
          <div className="flex items-center gap-4">
            <span>📞 Customer Care: 1800-123-4567</span>
            <span className="hidden md:inline">✉️ support@imart.com</span>
          </div>
          <div className="flex items-center gap-4">
            <Link to="/track-order" className="hover:text-accent transition-colors">Track Order</Link>
            <span className="hidden md:inline">🚚 Free Delivery on orders above ₹499</span>
          </div>
        </div>
      </div>

      {/* Main Header */}
      <div className="container-custom py-4">
        <div className="flex items-center justify-between gap-4">
          {/* Logo */}
          <Link to="/" className="flex items-center gap-2">
            <div className="text-3xl">🛒</div>
            <div>
              <h1 className="text-2xl font-bold text-primary">I-Mart</h1>
              <p className="text-xs text-gray-600">Shop Smart, Save More</p>
            </div>
          </Link>

          {/* Search Bar */}
          <form onSubmit={handleSearch} className="hidden md:flex flex-1 max-w-2xl">
            <input
              type="text"
              placeholder="Search for products, brands and more..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="flex-1 px-4 py-3 border-2 border-gray-300 rounded-l-lg focus:outline-none focus:border-primary"
            />
            <button
              type="submit"
              className="bg-primary text-white px-6 py-3 rounded-r-lg hover:bg-opacity-90 transition-all"
            >
              🔍
            </button>
          </form>

          {/* Right Side Icons */}
          <div className="flex items-center gap-4">
            {isAuthenticated ? (
              <div className="relative group">
                <button className="flex items-center gap-2 hover:text-primary transition-colors">
                  <span className="text-2xl">👤</span>
                  <div className="hidden lg:block text-left">
                    <p className="text-xs text-gray-600">Hello,</p>
                    <p className="font-semibold">{user?.name?.split(' ')[0]}</p>
                  </div>
                </button>
                <div className="absolute right-0 mt-2 w-48 bg-white rounded-lg shadow-xl opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all duration-300">
                  <Link to="/profile" className="block px-4 py-3 hover:bg-gray-100 transition-colors">
                    My Profile
                  </Link>
                  <Link to="/orders" className="block px-4 py-3 hover:bg-gray-100 transition-colors">
                    My Orders
                  </Link>
                  <Link to="/wishlist" className="block px-4 py-3 hover:bg-gray-100 transition-colors">
                    Wishlist
                  </Link>
                  <button
                    onClick={handleLogout}
                    className="block w-full text-left px-4 py-3 hover:bg-gray-100 transition-colors text-red-600"
                  >
                    Logout
                  </button>
                </div>
              </div>
            ) : (
              <Link to="/login" className="flex items-center gap-2 hover:text-primary transition-colors">
                <span className="text-2xl">👤</span>
                <div className="hidden lg:block text-left">
                  <p className="text-xs text-gray-600">Hello,</p>
                  <p className="font-semibold">Sign In</p>
                </div>
              </Link>
            )}

            <Link to="/cart" className="relative hover:text-primary transition-colors">
              <span className="text-3xl">🛒</span>
              {getCartCount() > 0 && (
                <span className="absolute -top-2 -right-2 bg-primary text-white text-xs font-bold rounded-full w-5 h-5 flex items-center justify-center">
                  {getCartCount()}
                </span>
              )}
            </Link>

            <button
              onClick={() => setShowMobileMenu(!showMobileMenu)}
              className="md:hidden text-2xl"
            >
              ☰
            </button>
          </div>
        </div>

        {/* Mobile Search */}
        <form onSubmit={handleSearch} className="md:hidden mt-4 flex">
          <input
            type="text"
            placeholder="Search products..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            className="flex-1 px-4 py-2 border-2 border-gray-300 rounded-l-lg focus:outline-none focus:border-primary"
          />
          <button
            type="submit"
            className="bg-primary text-white px-4 py-2 rounded-r-lg"
          >
            🔍
          </button>
        </form>
      </div>

      {/* Navigation */}
      <nav className="bg-gray-100 border-t border-gray-200">
        <div className="container-custom">
          <ul className="hidden md:flex items-center justify-center gap-8 py-3">
            <li>
              <Link to="/products?category=electronics" className="hover:text-primary transition-colors font-medium">
                Electronics
              </Link>
            </li>
            <li>
              <Link to="/products?category=fashion" className="hover:text-primary transition-colors font-medium">
                Fashion
              </Link>
            </li>
            <li>
              <Link to="/products?category=home-kitchen" className="hover:text-primary transition-colors font-medium">
                Home & Kitchen
              </Link>
            </li>
            <li>
              <Link to="/products?category=beauty" className="hover:text-primary transition-colors font-medium">
                Beauty
              </Link>
            </li>
            <li>
              <Link to="/products?category=sports" className="hover:text-primary transition-colors font-medium">
                Sports
              </Link>
            </li>
            <li>
              <Link to="/products?category=books" className="hover:text-primary transition-colors font-medium">
                Books
              </Link>
            </li>
            <li>
              <Link to="/products?category=toys" className="hover:text-primary transition-colors font-medium">
                Toys
              </Link>
            </li>
            <li>
              <Link to="/products?category=grocery" className="hover:text-primary transition-colors font-medium">
                Grocery
              </Link>
            </li>
          </ul>
        </div>
      </nav>

      {/* Mobile Menu */}
      {showMobileMenu && (
        <div className="md:hidden bg-white border-t border-gray-200 slide-in">
          <div className="container-custom py-4">
            <ul className="space-y-3">
              <li>
                <Link
                  to="/products?category=electronics"
                  className="block py-2 hover:text-primary transition-colors"
                  onClick={() => setShowMobileMenu(false)}
                >
                  📱 Electronics
                </Link>
              </li>
              <li>
                <Link
                  to="/products?category=fashion"
                  className="block py-2 hover:text-primary transition-colors"
                  onClick={() => setShowMobileMenu(false)}
                >
                  👕 Fashion
                </Link>
              </li>
              <li>
                <Link
                  to="/products?category=home-kitchen"
                  className="block py-2 hover:text-primary transition-colors"
                  onClick={() => setShowMobileMenu(false)}
                >
                  🏠 Home & Kitchen
                </Link>
              </li>
              <li>
                <Link
                  to="/products?category=beauty"
                  className="block py-2 hover:text-primary transition-colors"
                  onClick={() => setShowMobileMenu(false)}
                >
                  💄 Beauty
                </Link>
              </li>
              <li>
                <Link
                  to="/products?category=sports"
                  className="block py-2 hover:text-primary transition-colors"
                  onClick={() => setShowMobileMenu(false)}
                >
                  ⚽ Sports
                </Link>
              </li>
              <li>
                <Link
                  to="/products?category=books"
                  className="block py-2 hover:text-primary transition-colors"
                  onClick={() => setShowMobileMenu(false)}
                >
                  📚 Books
                </Link>
              </li>
              <li>
                <Link
                  to="/products?category=toys"
                  className="block py-2 hover:text-primary transition-colors"
                  onClick={() => setShowMobileMenu(false)}
                >
                  🎮 Toys
                </Link>
              </li>
              <li>
                <Link
                  to="/products?category=grocery"
                  className="block py-2 hover:text-primary transition-colors"
                  onClick={() => setShowMobileMenu(false)}
                >
                  🛒 Grocery
                </Link>
              </li>
            </ul>
          </div>
        </div>
      )}
    </header>
  );
};

export default Header;
