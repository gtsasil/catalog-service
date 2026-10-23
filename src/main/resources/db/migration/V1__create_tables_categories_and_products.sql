CREATE TABLE tb_categories(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE tb_products(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    price NUMERIC(12, 2) NOT NULL,
    stock_quantity INTEGER NOT NULL DEFAULT 0,
    category_id UUID NOT NULL,
    CONSTRAINT fk_products_category FOREIGN KEY (category_id) REFERENCES tb_categories(id)
);

CREATE INDEX idx_products_category ON tb_products(category_id);
CREATE INDEX idx_products_name ON tb_products(name);
