USE AssignmentJava2;
GO

-- Cập nhật lại cột status cho khớp chính xác từng chữ cái
UPDATE DOCUMENT_COPY 
SET status = 'AVAILABLE' -- Thử viết hoa hết lên nếu code bạn bà để Enum viết hoa
WHERE status = 'Available';

-- Hoặc nếu bạn bà dùng tiếng Việt, bà thử:
-- UPDATE DOCUMENT_COPY SET status = 'CON_TRONG' ...