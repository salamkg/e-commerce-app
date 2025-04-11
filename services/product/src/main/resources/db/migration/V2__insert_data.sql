-- Insert dummy data into category table
INSERT INTO public.category (id, description, name)
VALUES
     (1, 'Electronics and gadgets', 'Electronics'),
     (2, 'Home appliances', 'Appliances'),
     (3, 'Furniture and decor', 'Furniture'),
     (4, 'Clothing and accessories', 'Clothing'),
     (5, 'Books and stationery', 'Books');

-- Insert dummy data into product table
INSERT INTO public.product (id, description, name, available_quantity, price, category_id)
VALUES
    (1, 'Smartphone with 6GB RAM and 128GB Storage', 'Smartphone', 150, 799.99, 1),
    (2, 'Washing machine with 10kg capacity', 'Washing Machine', 50, 499.99, 2),
    (3, 'Comfortable sofa set for living room', 'Sofa Set', 30, 899.99, 3),
    (4, 'Cotton t-shirt with modern design', 'T-Shirt', 200, 19.99, 4),
    (5, 'Best-selling novel by famous author', 'Book', 100, 15.99, 5);