-- Test-Nutzer
-- Password ist für beide Users "admin123"
INSERT INTO public.users(email, password_hash, role) VALUES ('admin@shwiri-shop.de', 'PBKDF2WithHmacSHA256:3072:tmC6oP6V3qQsebGdNXocX/hsCwSnnJvHbQchfWiM+AA=:oij2SoMM9qDi4mLNqohvxOs1fMBhBMaSzA+2lrm679M=', 'ADMIN');
INSERT INTO public.users(email, password_hash, role) VALUES ('test@testee.de', 'PBKDF2WithHmacSHA256:3072:tmC6oP6V3qQsebGdNXocX/hsCwSnnJvHbQchfWiM+AA=:oij2SoMM9qDi4mLNqohvxOs1fMBhBMaSzA+2lrm679M=', 'CUSTOMER');
-- Test-Kategorien
INSERT INTO public.categories(name, description) VALUES ( 'Elektronik', 'Smartphones, Laptops und mehr');
INSERT INTO public.categories(name, description) VALUES ( 'Bücher', 'Fachliteratur und Romane');
INSERT INTO public.categories(name, description) VALUES ( 'Lebensmittel', 'Gemüse, Obst und mehr');
-- Test-Produkte
INSERT INTO public.products(name, price, stock_quantity, category_id) VALUES ( 'Enterprise Java Expert', 46.49, 50, 2);
INSERT INTO public.products(name, price, stock_quantity, category_id) VALUES ( 'C# für Developer', 60.98, 30, 2);
INSERT INTO public.products(name, price, stock_quantity, category_id) VALUES ( 'Mechanische Tastatur', 79.99, 20, 1);
INSERT INTO public.products(name, price, stock_quantity, category_id) VALUES ( 'Maus', 20.29, 5, 1);
INSERT INTO public.products(name, price, stock_quantity, category_id) VALUES ( 'Galaxy S25 Ultra', 1359.90, 10, 1);
-- Sequenzen abgleichen
SELECT setval('categories_seq', 10);
SELECT setval('products_seq', 10);
SELECT setval('users_seq', 10);