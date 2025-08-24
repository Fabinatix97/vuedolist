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
        menu: 'var(--menu)',
        menuborder: 'var(--menuborder)',
        primary: 'var(--primary)',
        primaryhover: 'var(--primaryhover)',
        text: 'var(--text)',
      },
    },
  },
  plugins: [],
}
