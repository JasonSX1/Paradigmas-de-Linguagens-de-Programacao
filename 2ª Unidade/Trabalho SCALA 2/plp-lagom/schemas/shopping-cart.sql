CREATE TABLE IF NOT EXISTS journal (
  ordering BIGINT AUTO_INCREMENT PRIMARY KEY,
  persistence_id VARCHAR(255) NOT NULL,
  sequence_number BIGINT NOT NULL,
  deleted BOOLEAN DEFAULT FALSE,
  tags VARCHAR(255),
  message BLOB NOT NULL,
  UNIQUE KEY (persistence_id, sequence_number)
);

CREATE TABLE IF NOT EXISTS snapshot (
  persistence_id VARCHAR(255) NOT NULL,
  sequence_number BIGINT NOT NULL,
  created BIGINT NOT NULL,
  snapshot BLOB NOT NULL,
  PRIMARY KEY (persistence_id, sequence_number)
);

CREATE TABLE read_side_offsets (
  read_side_id VARCHAR(255),
  tag VARCHAR(255),
  sequence_offset BIGINT,
  time_uuid_offset CHAR(36),
  PRIMARY KEY (read_side_id, tag)
);
