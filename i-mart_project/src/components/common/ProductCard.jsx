import { Link } from 'react-router-dom';
import { useCart } from '../../context/CartContext';

const ProductCard = ({ product }) => {
  const { addToCart } = useCart();

  const handleAddToCart = (e) => {
    e.preventDefault();
    addToCart(product);
  };

  return (
    <Link to={`/product/${product.id}`} className="card overflow-hidden group">
      {/* Image */}
      <div className="relative overflow-hidden bg-gray-100">
        <img
          src={product.image}
          alt={product.name}
          className="w-full h-64 object-cover group-hover:scale-110 transition-transform duration-300"
        />
        {product.discount > 0 && (
          <div className="absolute top-2 left-2 bg-primary text-white px-3 py-1 rounded-full text-sm font-bold">
            {product.discount}% OFF
          </div>
        )}
        {product.dealOfDay && (
          <div className="absolute top-2 right-2 bg-accent text-dark px-3 py-1 rounded-full text-sm font-bold">
            ⚡ Deal
          </div>
        )}
      </div>

      {/* Content */}
      <div className="p-4">
        {/* Brand */}
        <p className="text-xs text-gray-500 uppercase mb-1">{product.brand}</p>

        {/* Name */}
        <h3 className="font-semibold text-gray-800 mb-2 line-clamp-2 group-hover:text-primary transition-colors">
          {product.name}
        </h3>

        {/* Rating */}
        <div className="flex items-center gap-2 mb-2">
          <div className="flex items-center bg-green-600 text-white px-2 py-1 rounded text-xs">
            <span>{product.rating}</span>
            <span className="ml-1">⭐</span>
          </div>
          <span className="text-xs text-gray-500">({product.reviews})</span>
        </div>

        {/* Price */}
        <div className="flex items-center gap-2 mb-3">
          <span className="text-2xl font-bold text-gray-900">₹{product.price}</span>
          {product.originalPrice > product.price && (
            <>
              <span className="text-sm text-gray-500 line-through">₹{product.originalPrice}</span>
              <span className="text-sm text-green-600 font-semibold">
                {product.discount}% off
              </span>
            </>
          )}
        </div>

        {/* Stock Status */}
        {product.inStock ? (
          <button
            onClick={handleAddToCart}
            className="w-full bg-primary text-white py-2 rounded-lg font-semibold hover:bg-opacity-90 transition-all"
          >
            Add to Cart
          </button>
        ) : (
          <button
            disabled
            className="w-full bg-gray-300 text-gray-600 py-2 rounded-lg font-semibold cursor-not-allowed"
          >
            Out of Stock
          </button>
        )}
      </div>
    </Link>
  );
};

export default ProductCard;
