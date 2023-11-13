-- schema.sql
-- 데이터베이스 테이블 생성
CREATE TABLE IF NOT EXISTS git_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    owner VARCHAR(255),
    repository VARCHAR(255),
    version VARCHAR(255),
    description VARCHAR(255)
    );