ALTER TABLE users
    ADD CONSTRAINT unique_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT unique_cpf UNIQUE (cpf);

ALTER TABLE product_category
    ADD CONSTRAINT unique_category UNIQUE (name);

ALTER TABLE coupon
    ADD CONSTRAINT unique_coupon UNIQUE (code);
