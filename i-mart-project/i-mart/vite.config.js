import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: true,
    port: 5173,
    allowedHosts: ['5173-idrnaszl19dphz8r11o0m-2afdc3e2.manus-asia.computer'],
  },
})
