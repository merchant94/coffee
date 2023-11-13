-- data.sql
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