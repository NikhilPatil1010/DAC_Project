import { Link } from 'react-router-dom';

const Footer = () => {
  return (
    <footer className="bg-dark text-white mt-16">
      <div className="container-custom py-12">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          {/* About */}
          <div>
            <h3 className="text-xl font-bold mb-4 text-accent">About I-Mart</h3>
            <p className="text-gray-300 mb-4">
              Your one-stop destination for quality products at unbeatable prices. 
              Shop smart, save more with I-Mart!
            </p>
            <div className="flex gap-4">
              <a href="#" className="text-2xl hover:text-accent transition-colors">📘</a>
              <a href="#" className="text-2xl hover:text-accent transition-colors">📷</a>
              <a href="#" className="text-2xl hover:text-accent transition-colors">🐦</a>
              <a href="#" className="text-2xl hover:text-accent transition-colors">▶️</a>
            </div>
          </div>

          {/* Quick Links */}
          <div>
            <h3 className="text-xl font-bold mb-4 text-accent">Quick Links</h3>
            <ul className="space-y-2">
              <li>
                <Link to="/about" className="text-gray-300 hover:text-accent transition-colors">
                  About Us
                </Link>
              </li>
              <li>
                <Link to="/contact" className="text-gray-300 hover:text-accent transition-colors">
                  Contact Us
                </Link>
              </li>
              <li>
                <Link to="/careers" className="text-gray-300 hover:text-accent transition-colors">
                  Careers
                </Link>
              </li>
              <li>
                <Link to="/blog" className="text-gray-300 hover:text-accent transition-colors">
                  Blog
                </Link>
              </li>
              <li>
                <Link to="/press" className="text-gray-300 hover:text-accent transition-colors">
                  Press
                </Link>
              </li>
            </ul>
          </div>

          {/* Customer Service */}
          <div>
            <h3 className="text-xl font-bold mb-4 text-accent">Customer Service</h3>
            <ul className="space-y-2">
              <li>
                <Link to="/help" className="text-gray-300 hover:text-accent transition-colors">
                  Help Center
                </Link>
              </li>
              <li>
                <Link to="/track-order" className="text-gray-300 hover:text-accent transition-colors">
                  Track Order
                </Link>
              </li>
              <li>
                <Link to="/returns" className="text-gray-300 hover:text-accent transition-colors">
                  Returns & Refunds
                </Link>
              </li>
              <li>
                <Link to="/shipping" className="text-gray-300 hover:text-accent transition-colors">
                  Shipping Info
                </Link>
              </li>
              <li>
                <Link to="/faq" className="text-gray-300 hover:text-accent transition-colors">
                  FAQs
                </Link>
              </li>
            </ul>
          </div>

          {/* Contact Info */}
          <div>
            <h3 className="text-xl font-bold mb-4 text-accent">Contact Info</h3>
            <ul className="space-y-3 text-gray-300">
              <li className="flex items-start gap-2">
                <span>📍</span>
                <span>123 Shopping Street, Mumbai, Maharashtra 400001</span>
              </li>
              <li className="flex items-center gap-2">
                <span>📞</span>
                <span>1800-123-4567</span>
              </li>
              <li className="flex items-center gap-2">
                <span>✉️</span>
                <span>support@imart.com</span>
              </li>
              <li className="flex items-center gap-2">
                <span>🕐</span>
                <span>Mon - Sat: 9:00 AM - 9:00 PM</span>
              </li>
            </ul>
          </div>
        </div>

        {/* Payment Methods */}
        <div className="mt-12 pt-8 border-t border-gray-700">
          <div className="flex flex-col md:flex-row justify-between items-center gap-4">
            <div>
              <h4 className="font-semibold mb-2">We Accept</h4>
              <div className="flex gap-3 text-2xl">
                <span>💳</span>
                <span>💵</span>
                <span>📱</span>
                <span>🏦</span>
              </div>
            </div>
            <div className="text-center md:text-right">
              <p className="text-gray-300">
                © 2024 I-Mart. All rights reserved.
              </p>
              <div className="flex gap-4 mt-2 justify-center md:justify-end">
                <Link to="/privacy" className="text-gray-300 hover:text-accent transition-colors text-sm">
                  Privacy Policy
                </Link>
                <Link to="/terms" className="text-gray-300 hover:text-accent transition-colors text-sm">
                  Terms & Conditions
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
