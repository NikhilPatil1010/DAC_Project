import { Link } from 'react-router-dom';
import ProductCard from '../components/common/ProductCard';
import { categories, getDealOfDayProducts, products } from '../data/products';

const HomePage = () => {
  const dealProducts = getDealOfDayProducts();
  const featuredProducts = products.slice(0, 8);

  return (
    <div className="min-h-screen">
      {/* Hero Section */}
      <section className="bg-gradient-to-r from-primary to-secondary text-white py-20">
        <div className="container-custom">
          <div className="max-w-3xl fade-in">
            <h1 className="text-5xl md:text-6xl font-bold mb-6">
              Welcome to I-Mart
            </h1>
            <p className="text-xl md:text-2xl mb-8">
              Your one-stop destination for quality products at unbeatable prices!
            </p>
            <div className="flex flex-wrap gap-4">
              <Link to="/products" className="bg-white text-primary px-8 py-4 rounded-lg font-bold text-lg hover:bg-opacity-90 transition-all shadow-lg">
                Shop Now
              </Link>
              <Link to="/products?category=electronics" className="bg-accent text-dark px-8 py-4 rounded-lg font-bold text-lg hover:bg-opacity-90 transition-all shadow-lg">
                Today's Deals
              </Link>
            </div>
          </div>
        </div>
      </section>

      {/* Features */}
      <section className="py-12 bg-light">
        <div className="container-custom">
          <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
            <div className="flex items-center gap-4 bg-white p-6 rounded-lg shadow-md">
              <span className="text-4xl">🚚</span>
              <div>
                <h3 className="font-bold text-lg">Free Delivery</h3>
                <p className="text-sm text-gray-600">On orders above ₹499</p>
              </div>
            </div>
            <div className="flex items-center gap-4 bg-white p-6 rounded-lg shadow-md">
              <span className="text-4xl">💯</span>
              <div>
                <h3 className="font-bold text-lg">100% Genuine</h3>
                <p className="text-sm text-gray-600">Authentic products</p>
              </div>
            </div>
            <div className="flex items-center gap-4 bg-white p-6 rounded-lg shadow-md">
              <span className="text-4xl">🔄</span>
              <div>
                <h3 className="font-bold text-lg">Easy Returns</h3>
                <p className="text-sm text-gray-600">7-day return policy</p>
              </div>
            </div>
            <div className="flex items-center gap-4 bg-white p-6 rounded-lg shadow-md">
              <span className="text-4xl">🔒</span>
              <div>
                <h3 className="font-bold text-lg">Secure Payment</h3>
                <p className="text-sm text-gray-600">100% secure transactions</p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Categories */}
      <section className="py-16">
        <div className="container-custom">
          <h2 className="text-3xl font-bold mb-8 text-center">Shop by Category</h2>
          <div className="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-8 gap-4">
            {categories.map((category) => (
              <Link
                key={category.id}
                to={`/products?category=${category.slug}`}
                className="card p-6 text-center hover:shadow-2xl transition-all group"
              >
                <div className="text-5xl mb-3 group-hover:scale-110 transition-transform">
                  {category.icon}
                </div>
                <h3 className="font-semibold text-sm">{category.name}</h3>
              </Link>
            ))}
          </div>
        </div>
      </section>

      {/* Deals of the Day */}
      <section className="py-16 bg-light">
        <div className="container-custom">
          <div className="flex items-center justify-between mb-8">
            <div>
              <h2 className="text-3xl font-bold mb-2">⚡ Deals of the Day</h2>
              <p className="text-gray-600">Limited time offers - Grab them before they're gone!</p>
            </div>
            <Link to="/products?deals=true" className="btn-outline hidden md:inline-block">
              View All Deals
            </Link>
          </div>
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
            {dealProducts.map((product) => (
              <ProductCard key={product.id} product={product} />
            ))}
          </div>
          <div className="text-center mt-8 md:hidden">
            <Link to="/products?deals=true" className="btn-primary">
              View All Deals
            </Link>
          </div>
        </div>
      </section>

      {/* Featured Products */}
      <section className="py-16">
        <div className="container-custom">
          <div className="flex items-center justify-between mb-8">
            <div>
              <h2 className="text-3xl font-bold mb-2">Featured Products</h2>
              <p className="text-gray-600">Handpicked products just for you</p>
            </div>
            <Link to="/products" className="btn-outline hidden md:inline-block">
              View All Products
            </Link>
          </div>
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
            {featuredProducts.map((product) => (
              <ProductCard key={product.id} product={product} />
            ))}
          </div>
          <div className="text-center mt-8 md:hidden">
            <Link to="/products" className="btn-primary">
              View All Products
            </Link>
          </div>
        </div>
      </section>

      {/* Newsletter */}
      <section className="py-16 bg-gradient-to-r from-secondary to-primary text-white">
        <div className="container-custom text-center">
          <h2 className="text-3xl font-bold mb-4">Subscribe to Our Newsletter</h2>
          <p className="text-lg mb-8">Get exclusive deals and updates delivered to your inbox!</p>
          <form className="max-w-md mx-auto flex gap-2">
            <input
              type="email"
              placeholder="Enter your email"
              className="flex-1 px-4 py-3 rounded-lg text-gray-900 focus:outline-none"
            />
            <button
              type="submit"
              className="bg-accent text-dark px-8 py-3 rounded-lg font-bold hover:bg-opacity-90 transition-all"
            >
              Subscribe
            </button>
          </form>
        </div>
      </section>
    </div>
  );
};

export default HomePage;
