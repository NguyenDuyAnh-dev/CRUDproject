-- Tạo cơ sở dữ liệu
CREATE DATABASE dbwebtrenlop;
GO

USE dbwebtrenlop;
GO

-- Tạo bảng OrderDetail
CREATE TABLE OrderDetail (
    id INT PRIMARY KEY IDENTITY(1,1),
    Producted NVARCHAR(100),
    UnitPrice DECIMAL(10, 2),
    Quantity INT,
    OrderId INT,
    Total DECIMAL(10, 2) AS (UnitPrice * Quantity) PERSISTED
);
GO

-- Chèn dữ liệu giả lập vào bảng OrderDetail
INSERT INTO OrderDetail (Producted, UnitPrice, Quantity, OrderId)
VALUES 
('Harry Potter và Hòn Đá Phù Thủy', 120000, 1, 1),
('Nhà Giả Kim', 150000, 2, 1),
('Moby Dick', 180000, 1, 2);
GO

-- Tạo bảng Register
CREATE TABLE Register (
    Username NVARCHAR(50) PRIMARY KEY,
    Password NVARCHAR(50),
    LastName NVARCHAR(100),
    IsAdmin BIT
);
GO

-- Chèn dữ liệu giả lập vào bảng Register
INSERT INTO Register (Username, Password, LastName, IsAdmin)
VALUES 
('user1', 'password1', 'Tran Minh Tu', 0),
('admin', 'admin123', 'Hoang Thi Mai', 1);
GO

-- Tạo bảng TblOrder
CREATE TABLE TblOrder (
    id INT PRIMARY KEY IDENTITY(1,1),
    Date DATETIME DEFAULT GETDATE(),
    Customer NVARCHAR(100),
    Address NVARCHAR(255),
    Email NVARCHAR(100),
    Total DECIMAL(10, 2)
);
GO

-- Chèn dữ liệu giả lập vào bảng TblOrder
INSERT INTO TblOrder (Date, Customer, Address, Email, Total)
VALUES 
(GETDATE(), 'Tran Minh Tu', '123 Đường A', 'tranminhtu@example.com', 300000),
(GETDATE(), 'Hoang Thi Mai', '456 Đường B', 'hoangthimai@example.com', 150000);
GO

-- Tạo bảng TblProduct
CREATE TABLE TblProduct (
    SKU NVARCHAR(50) PRIMARY KEY,
    Name NVARCHAR(100),
    Description NVARCHAR(255),
    Quantity INT,
    Price DECIMAL(10, 2),
    Status NVARCHAR(20)
);
GO

-- Chèn dữ liệu giả lập vào bảng TblProduct
INSERT INTO TblProduct (SKU, Name, Description, Quantity, Price, Status)
VALUES 
('SKU001', 'Harry Potter và Hòn Đá Phù Thủy', 'Tiểu thuyết giả tưởng của J.K. Rowling', 10, 120000, 'Còn hàng'),
('SKU002', 'Nhà Giả Kim', 'Tiểu thuyết của Paulo Coelho', 5, 150000, 'Còn hàng'),
('SKU003', 'Moby Dick', 'Tiểu thuyết của Herman Melville', 0, 180000, 'Hết hàng');
GO
