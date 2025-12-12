// This script sets up demo data for testing
const demoUser = {
  id: 1,
  name: 'Demo User',
  email: 'demo@imart.com',
  phone: '9876543210',
  password: 'demo123',
  createdAt: new Date().toISOString(),
};

console.log('Demo user data:');
console.log(JSON.stringify([demoUser]));
