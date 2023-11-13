-- data.sql
-- 데이터베이스 테이블 생성
CREATE TABLE IF NOT EXISTS git_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    owner VARCHAR(255),
    repository VARCHAR(255),
    version VARCHAR(255),
    description VARCHAR(255)
    );

-- 초기 데이터 추가
MERGE INTO git_info (owner, repository, version, description) KEY (owner, repository)
    VALUES
    ('stacks-network', 'stacks-core',  '2.4.0.0.0', 'STX mainnet node'),
    ('steemit', 'steem', '', 'STEEM mainnet node'),
    ('openhive-network', 'hive', '', 'HIVE mainnet node'),
    ('astarnetwork', 'astar', 'v5.0.0', 'ASTR mainnet node'),
    ('symbol', 'symbol', '', 'XYM mainnet node'),
    ('nervosnetwork', 'ckb', '', 'CKB mainnet node'),
    ('icon-project', 'goloop', '', 'ICX mainnet node'),
    ('METADIUM', 'go-metadium', '', 'META mainnet node'),
    ('CortexFoundation', 'CortexTheseus', '', 'CTXC mainnet node'),
    ('iost-official', 'go-iost', '', 'IOST mainnet node'),
    ('celo-org', 'celo-blockchain', '', 'CELO mainnet node');