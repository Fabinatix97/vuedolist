/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
        body: 'var(--body)',
        border: 'var(--border)',
        info: 'var(--info)',
        main: 'var(--main)',
        primary: 'var(--primary)',
        primaryhover: 'var(--primaryhover)',
        secondary: 'var(--secondary)',
        secondaryhover: 'var(--secondaryhover)',
        text: 'var(--text)',
      },
    },
  },
  plugins: [],
}
