# I-Mart - Modern E-Commerce Website

A fully functional e-commerce website built with React.js, Vite, Tailwind CSS, and React Router. This project demonstrates a complete online shopping experience with authentication, product browsing, cart management, and checkout functionality.

## 🚀 Features

### User Features
- **Authentication System**
  - User registration with validation
  - Login/Logout functionality
  - Profile management
  - Demo credentials available for testing

- **Product Browsing**
  - 40+ products across 8 categories
  - Advanced filtering by category, price range, and rating
  - Product search functionality
  - Sorting options (price, rating, discount)
  - Detailed product pages with reviews

- **Shopping Cart**
  - Add/remove products
  - Update quantities
  - Real-time cart total calculation
  - Persistent cart (localStorage)
  - Cart count badge

- **Checkout Process**
  - Shipping address form
  - Multiple payment options (COD, Card, UPI, Net Banking)
  - Order summary
  - Form validation
  - Order confirmation page

- **Order Management**
  - Order history
  - Order details
  - Order status tracking
  - Delivery information

- **User Profile**
  - View and edit profile information
  - Order statistics
  - Account management

### UI/UX Features
- **Responsive Design**
  - Mobile-first approach
  - Tablet and desktop optimized
  - Hamburger menu for mobile

- **Modern UI**
  - Clean and attractive design
  - Smooth animations and transitions
  - Custom color scheme
  - Intuitive navigation

- **Performance**
  - Fast page loads with Vite
  - Optimized images
  - Efficient state management

## 🛠️ Tech Stack

- **Frontend Framework**: React.js 19.2.1
- **Build Tool**: Vite 7.2.7
- **Routing**: React Router DOM 7.10.1
- **Styling**: Tailwind CSS 4.1.17
- **State Management**: React Context API
- **Data Storage**: localStorage (for demo purposes)

## 📦 Installation

1. **Navigate to the project directory**
   ```bash
   cd i-mart
   ```

2. **Install dependencies**
   ```bash
   pnpm install
   ```

3. **Start the development server**
   ```bash
   pnpm dev
   ```

4. **Open your browser**
   Navigate to `http://localhost:5173`

## 🎯 Usage

### Demo Credentials
For testing the authentication system, use:
- **Email**: demo@imart.com
- **Password**: demo123

Or create a new account through the registration page.

### Testing the Application

1. **Browse Products**
   - Visit the homepage
   - Click on categories or "Shop Now"
   - Use filters and search

2. **Add to Cart**
   - Click on any product
   - View details and click "Add to Cart"
   - Adjust quantities in cart

3. **Checkout**
   - Proceed to checkout from cart
   - Fill in shipping details
   - Select payment method
   - Place order

4. **View Orders**
   - Login to your account
   - Navigate to "My Orders"
   - View order history and details

## 📁 Project Structure

```
i-mart/
├── src/
│   ├── components/
│   │   ├── common/          # Reusable components
│   │   │   └── ProductCard.jsx
│   │   └── layout/          # Layout components
│   │       ├── Header.jsx
│   │       └── Footer.jsx
│   ├── context/             # Context providers
│   │   ├── AuthContext.jsx
│   │   └── CartContext.jsx
│   ├── data/                # Mock data
│   │   └── products.js
│   ├── pages/               # Page components
│   │   ├── HomePage.jsx
│   │   ├── ProductsPage.jsx
│   │   ├── ProductDetailPage.jsx
│   │   ├── LoginPage.jsx
│   │   ├── RegisterPage.jsx
│   │   ├── CartPage.jsx
│   │   ├── CheckoutPage.jsx
│   │   ├── OrderSuccessPage.jsx
│   │   ├── ProfilePage.jsx
│   │   └── OrdersPage.jsx
│   ├── App.jsx              # Main app component
│   ├── main.jsx             # Entry point
│   └── index.css            # Global styles
├── public/                  # Static assets
├── index.html               # HTML template
├── package.json             # Dependencies
├── tailwind.config.js       # Tailwind configuration
├── vite.config.js           # Vite configuration
└── README.md                # This file
```

## 🎨 Customization

### Colors
Edit `tailwind.config.js` to customize the color scheme:
```javascript
colors: {
  primary: '#FF6B35',    // Main brand color
  secondary: '#004E89',  // Secondary color
  accent: '#F7B801',     // Accent color
  dark: '#1A1A2E',       // Dark text
  light: '#F5F5F5',      // Light background
}
```

### Products
Add or modify products in `src/data/products.js`

### Categories
Update categories in `src/data/products.js`

## 🚀 Build for Production

```bash
pnpm build
```

The production-ready files will be in the `dist/` directory.

To preview the production build:
```bash
pnpm preview
```

## 📝 Available Scripts

- `pnpm dev` - Start development server
- `pnpm build` - Build for production
- `pnpm preview` - Preview production build
- `pnpm lint` - Run ESLint

## 🤝 Contributing

This is a demo project for educational purposes. Feel free to fork and modify as needed.

## 📄 License

MIT License - feel free to use this project for learning and development.

## 👨‍💻 Developer Notes

### State Management
- Uses React Context API for global state (Auth & Cart)
- localStorage for data persistence
- No external state management library required

### Authentication
- Mock authentication system
- Stores user data in localStorage
- Password stored as plain text (for demo only - use proper encryption in production)

### Data Storage
- All data stored in localStorage
- Users, cart, and orders persist across sessions
- Clear localStorage to reset application state

### Responsive Breakpoints
- Mobile: < 768px
- Tablet: 768px - 1024px
- Desktop: > 1024px

## 🐛 Known Issues

- Images use external URLs (Unsplash) - may be slow to load
- No backend integration - all data is mock
- No real payment processing
- Basic form validation

---

**Built with ❤️ using React.js and Vite**
