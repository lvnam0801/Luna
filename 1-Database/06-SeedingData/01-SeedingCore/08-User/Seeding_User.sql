USE LunaWMS;

-- Clear old data and reset identity
DELETE FROM User;
ALTER TABLE User AUTO_INCREMENT = 1;

-- Insert users (password: 0123456789)
INSERT INTO User (UserName, Password, FirstName, LastName, FullName, PhotoURL, Email, Phone, Status, RoleID) VALUES
('nam.admin',  '$2a$10$HkXgtjHUWxHUMTfHPIF06OlQ1k5Fq7zzDkXeebjKqKL8BiJgYeqsu', 'Nam', 'Admin', 'Nam Admin', NULL, 'nam.admin@example.com', '0901123123', 'active', 1),
('quyen.kho',  '$2a$10$HkXgtjHUWxHUMTfHPIF06OlQ1k5Fq7zzDkXeebjKqKL8BiJgYeqsu', 'Quyền', 'Kho', 'Quyền Kho', NULL, 'quyen.kho@example.com', '0909988776', 'active', 2),
('hien.ketoan','$2a$10$HkXgtjHUWxHUMTfHPIF06OlQ1k5Fq7zzDkXeebjKqKL8BiJgYeqsu', 'Hiền', 'Kế Toán', 'Hiền Kế Toán', NULL, 'hien.ketoan@example.com', '0904433221', 'active', 3),
('tai.vc',     '$2a$10$HkXgtjHUWxHUMTfHPIF06OlQ1k5Fq7zzDkXeebjKqKL8BiJgYeqsu', 'Tài', 'Vận Chuyển', 'Tài Vận Chuyển', NULL, 'tai.vc@example.com', '0912345678', 'active', 4),
('thu.kh',     '$2a$10$HkXgtjHUWxHUMTfHPIF06OlQ1k5Fq7zzDkXeebjKqKL8BiJgYeqsu', 'Thư', 'Khách', 'Thư Khách', NULL, 'thu.kh@example.com', '0932123456', 'active', 5);