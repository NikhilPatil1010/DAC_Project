import { useState, useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import ProductCard from '../components/common/ProductCard';
import { products, categories, searchProducts, getProductsByCategory } from '../data/products';

const ProductsPage = () => {
  const [searchParams] = useSearchParams();
  const [filteredProducts, setFilteredProducts] = useState(products);
  const [selectedCategory, setSelectedCategory] = useState('all');
  const [priceRange, setPriceRange] = useState('all');
  const [sortBy, setSortBy] = useState('featured');
  const [showFilters, setShowFilters] = useState(false);

  useEffect(() => {
    let result = [...products];

    // Filter by search query
    const searchQuery = searchParams.get('search');
    if (searchQuery) {
      result = searchProducts(searchQuery);
    }

    // Filter by category from URL
    const categoryParam = searchParams.get('category');
    if (categoryParam) {
      result = getProductsByCategory(categoryParam);
      setSelectedCategory(categoryParam);
    } else if (selectedCategory !== 'all') {
      result = getProductsByCategory(selectedCategory);
    }

    // Filter by deals
    const dealsParam = searchParams.get('deals');
    if (dealsParam === 'true') {
      result = result.filter(p => p.dealOfDay);
    }

    // Filter by price range
    if (priceRange !== 'all') {
      const [min, max] = priceRange.split('-').map(Number);
      result = result.filter(p => {
        if (max) {
          return p.price >= min && p.price <= max;
        }
        return p.price >= min;
      });
    }

    // Sort products
    switch (sortBy) {
      case 'price-low':
        result.sort((a, b) => a.price - b.price);
        break;
      case 'price-high':
        result.sort((a, b) => b.price - a.price);
        break;
      case 'rating':
        result.sort((a, b) => b.rating - a.rating);
        break;
      case 'discount':
        result.sort((a, b) => b.discount - a.discount);
        break;
      default:
        // Featured - keep original order
        break;
    }

    setFilteredProducts(result);
  }, [searchParams, selectedCategory, priceRange, sortBy]);

  const handleCategoryChange = (category) => {
    setSelectedCategory(category);
  };

  return (
    <div className="min-h-screen bg-light py-8">
      <div className="container-custom">
        <div className="flex flex-col lg:flex-row gap-8">
          {/* Filters Sidebar */}
          <aside className={`lg:w-64 ${showFilters ? 'block' : 'hidden lg:block'}`}>
            <div className="card p-6 sticky top-24">
              <div className="flex items-center justify-between mb-6">
                <h2 className="text-xl font-bold">Filters</h2>
                <button
                  onClick={() => {
                    setSelectedCategory('all');
                    setPriceRange('all');
                    setSortBy('featured');
                  }}
                  className="text-sm text-primary hover:underline"
                >
                  Clear All
                </button>
              </div>

              {/* Categories */}
              <div className="mb-6">
                <h3 className="font-semibold mb-3">Categories</h3>
                <div className="space-y-2">
                  <label className="flex items-center cursor-pointer">
                    <input
                      type="radio"
                      name="category"
                      value="all"
                      checked={selectedCategory === 'all'}
                      onChange={() => handleCategoryChange('all')}
                      className="mr-2"
                    />
                    <span className="text-sm">All Products</span>
                  </label>
                  {categories.map((category) => (
                    <label key={category.id} className="flex items-center cursor-pointer">
                      <input
                        type="radio"
                        name="category"
                        value={category.slug}
                        checked={selectedCategory === category.slug}
                        onChange={() => handleCategoryChange(category.slug)}
                        className="mr-2"
                      />
                      <span className="text-sm">{category.name}</span>
                    </label>
                  ))}
                </div>
              </div>

              {/* Price Range */}
              <div className="mb-6">
                <h3 className="font-semibold mb-3">Price Range</h3>
                <div className="space-y-2">
                  <label className="flex items-center cursor-pointer">
                    <input
                      type="radio"
                      name="price"
                      value="all"
                      checked={priceRange === 'all'}
                      onChange={(e) => setPriceRange(e.target.value)}
                      className="mr-2"
                    />
                    <span className="text-sm">All Prices</span>
                  </label>
                  <label className="flex items-center cursor-pointer">
                    <input
                      type="radio"
                      name="price"
                      value="0-500"
                      checked={priceRange === '0-500'}
                      onChange={(e) => setPriceRange(e.target.value)}
                      className="mr-2"
                    />
                    <span className="text-sm">Under ₹500</span>
                  </label>
                  <label className="flex items-center cursor-pointer">
                    <input
                      type="radio"
                      name="price"
                      value="500-1000"
                      checked={priceRange === '500-1000'}
                      onChange={(e) => setPriceRange(e.target.value)}
                      className="mr-2"
                    />
                    <span className="text-sm">₹500 - ₹1000</span>
                  </label>
                  <label className="flex items-center cursor-pointer">
                    <input
                      type="radio"
                      name="price"
                      value="1000-2500"
                      checked={priceRange === '1000-2500'}
                      onChange={(e) => setPriceRange(e.target.value)}
                      className="mr-2"
                    />
                    <span className="text-sm">₹1000 - ₹2500</span>
                  </label>
                  <label className="flex items-center cursor-pointer">
                    <input
                      type="radio"
                      name="price"
                      value="2500-5000"
                      checked={priceRange === '2500-5000'}
                      onChange={(e) => setPriceRange(e.target.value)}
                      className="mr-2"
                    />
                    <span className="text-sm">₹2500 - ₹5000</span>
                  </label>
                  <label className="flex items-center cursor-pointer">
                    <input
                      type="radio"
                      name="price"
                      value="5000-999999"
                      checked={priceRange === '5000-999999'}
                      onChange={(e) => setPriceRange(e.target.value)}
                      className="mr-2"
                    />
                    <span className="text-sm">Above ₹5000</span>
                  </label>
                </div>
              </div>

              {/* Rating Filter */}
              <div className="mb-6">
                <h3 className="font-semibold mb-3">Customer Rating</h3>
                <div className="space-y-2">
                  {[4.5, 4.0, 3.5, 3.0].map((rating) => (
                    <label key={rating} className="flex items-center cursor-pointer">
                      <input type="checkbox" className="mr-2" />
                      <span className="text-sm">{rating}⭐ & above</span>
                    </label>
                  ))}
                </div>
              </div>

              {/* Discount Filter */}
              <div>
                <h3 className="font-semibold mb-3">Discount</h3>
                <div className="space-y-2">
                  {[50, 40, 30, 20, 10].map((discount) => (
                    <label key={discount} className="flex items-center cursor-pointer">
                      <input type="checkbox" className="mr-2" />
                      <span className="text-sm">{discount}% or more</span>
                    </label>
                  ))}
                </div>
              </div>
            </div>
          </aside>

          {/* Products Grid */}
          <main className="flex-1">
            {/* Header */}
            <div className="flex flex-col sm:flex-row items-start sm:items-center justify-between mb-6 gap-4">
              <div>
                <h1 className="text-2xl font-bold mb-1">
                  {searchParams.get('search')
                    ? `Search Results for "${searchParams.get('search')}"`
                    : searchParams.get('deals')
                    ? 'Deals of the Day'
                    : selectedCategory === 'all'
                    ? 'All Products'
                    : categories.find(c => c.slug === selectedCategory)?.name}
                </h1>
                <p className="text-gray-600">
                  Showing {filteredProducts.length} {filteredProducts.length === 1 ? 'product' : 'products'}
                </p>
              </div>

              <div className="flex items-center gap-4 w-full sm:w-auto">
                <button
                  onClick={() => setShowFilters(!showFilters)}
                  className="lg:hidden btn-outline flex-1 sm:flex-none"
                >
                  {showFilters ? 'Hide Filters' : 'Show Filters'}
                </button>
                <select
                  value={sortBy}
                  onChange={(e) => setSortBy(e.target.value)}
                  className="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary flex-1 sm:flex-none"
                >
                  <option value="featured">Featured</option>
                  <option value="price-low">Price: Low to High</option>
                  <option value="price-high">Price: High to Low</option>
                  <option value="rating">Customer Rating</option>
                  <option value="discount">Discount</option>
                </select>
              </div>
            </div>

            {/* Products Grid */}
            {filteredProducts.length > 0 ? (
              <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                {filteredProducts.map((product) => (
                  <ProductCard key={product.id} product={product} />
                ))}
              </div>
            ) : (
              <div className="text-center py-16">
                <div className="text-6xl mb-4">😢</div>
                <h2 className="text-2xl font-bold mb-2">No Products Found</h2>
                <p className="text-gray-600 mb-6">
                  Try adjusting your filters or search query
                </p>
                <button
                  onClick={() => {
                    setSelectedCategory('all');
                    setPriceRange('all');
                    setSortBy('featured');
                  }}
                  className="btn-primary"
                >
                  Clear Filters
                </button>
              </div>
            )}
          </main>
        </div>
      </div>
    </div>
  );
};

export default ProductsPage;
