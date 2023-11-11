-- data.sql
-- 데이터베이스 테이블 생성
CREATE TABLE IF NOT EXISTS git_repo_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    owner VARCHAR(255),
    repository VARCHAR(255),
    version VARCHAR(255),
    description VARCHAR(255)
    );

-- 초기 데이터 추가
MERGE INTO git_repo_info (owner, repository, version, description) KEY (owner, repository)
    VALUES
    ('stacks-network', 'stacks-blockchain', '2.4.0.0.0', 'STX mainnet node'),
    ('astarnetwork', 'astar', 'v5.0.0', 'ASTR mainnet node');