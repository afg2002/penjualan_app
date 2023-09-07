-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 07, 2023 at 05:06 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penjualan_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int NOT NULL,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `kategori_produk`
--

CREATE TABLE `kategori_produk` (
  `id_kategori` int NOT NULL,
  `nama_kategori` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `kategori_produk`
--

INSERT INTO `kategori_produk` (`id_kategori`, `nama_kategori`) VALUES
(1, 'Elektronik'),
(2, 'Fashion'),
(3, 'Kecantikan'),
(4, 'Olahraga'),
(5, 'Makanan');

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `id_member` int NOT NULL,
  `nama_member` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `alamat` text,
  `no_telpon` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id_member`, `nama_member`, `alamat`, `no_telpon`) VALUES
(1, 'John Doe', 'Jl. Bakso No.12', '2'),
(2, 'Jane Doe', 'Jl. Martabak No.21', ''),
(3, 'Mario', 'Jl. Semangka No.55', ''),
(4, 'Luigi', 'Jl. Jeruk No.77', ''),
(5, 'Bob', 'Jl. Apel No.19', '');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id_produk` int NOT NULL,
  `nama_produk` varchar(50) DEFAULT NULL,
  `harga` int DEFAULT NULL,
  `stok` int NOT NULL,
  `id_kategori` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id_produk`, `nama_produk`, `harga`, `stok`, `id_kategori`) VALUES
(1, 'Laptop', 2000000, 100, 1),
(2, 'Sepatu', 150000, 100, 2),
(3, 'Bedak', 25000, 100, 3),
(4, 'Bola Futsal', 150000, 98, 4);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_penjualan`
--

CREATE TABLE `transaksi_penjualan` (
  `id_transaksi` int NOT NULL,
  `id_member` int DEFAULT NULL,
  `id_produk` int DEFAULT NULL,
  `jumlah` int DEFAULT NULL,
  `tanggal_penjualan` date DEFAULT NULL,
  `total_harga` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transaksi_penjualan`
--

INSERT INTO `transaksi_penjualan` (`id_transaksi`, `id_member`, `id_produk`, `jumlah`, `tanggal_penjualan`, `total_harga`) VALUES
(10, 2, 3, 3, '2023-09-07', 300000),
(11, 2, 4, 2, '2023-09-07', 300000);

--
-- Triggers `transaksi_penjualan`
--
DELIMITER $$
CREATE TRIGGER `update_stock_after_insert` AFTER INSERT ON `transaksi_penjualan` FOR EACH ROW BEGIN
    DECLARE stok_produk_baru INT;
    SET stok_produk_baru = (SELECT stok FROM produk WHERE id_produk = NEW.id_produk);
    SET stok_produk_baru = stok_produk_baru - NEW.jumlah;
    IF stok_produk_baru < 0 THEN
        SET stok_produk_baru = 0;
    END IF;
    UPDATE produk SET stok = stok_produk_baru WHERE id_produk = NEW.id_produk;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_stock_after_update` AFTER UPDATE ON `transaksi_penjualan` FOR EACH ROW BEGIN
    DECLARE stok_produk_baru INT;
    DECLARE stok_produk_lama INT;
    IF NEW.id_produk != OLD.id_produk THEN
        SET stok_produk_baru = (SELECT stok FROM produk WHERE id_produk = NEW.id_produk);
        SET stok_produk_lama = (SELECT stok FROM produk WHERE id_produk = OLD.id_produk);
        SET stok_produk_baru = stok_produk_baru - NEW.jumlah;
        SET stok_produk_lama = stok_produk_lama + OLD.jumlah;
        IF stok_produk_baru < 0 THEN
            SET stok_produk_baru = 0;
        END IF;
        UPDATE produk SET stok = stok_produk_baru WHERE id_produk = NEW.id_produk;
        UPDATE produk SET stok = stok_produk_lama WHERE id_produk = OLD.id_produk;
    ELSE
        SET stok_produk_baru = (SELECT stok FROM produk WHERE id_produk = NEW.id_produk);
        SET stok_produk_baru = stok_produk_baru - NEW.jumlah + OLD.jumlah;
        IF stok_produk_baru < 0 THEN
            SET stok_produk_baru = 0;
        END IF;
        UPDATE produk SET stok = stok_produk_baru WHERE id_produk = NEW.id_produk;
    END IF;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `kategori_produk`
--
ALTER TABLE `kategori_produk`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id_member`) USING BTREE;

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_produk`),
  ADD KEY `id_kategori` (`id_kategori`);

--
-- Indexes for table `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_produk` (`id_produk`),
  ADD KEY `id_customer` (`id_member`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `kategori_produk`
--
ALTER TABLE `kategori_produk`
  MODIFY `id_kategori` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `members`
--
ALTER TABLE `members`
  MODIFY `id_member` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id_produk` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  MODIFY `id_transaksi` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `produk`
--
ALTER TABLE `produk`
  ADD CONSTRAINT `produk_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori_produk` (`id_kategori`);

--
-- Constraints for table `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD CONSTRAINT `transaksi_penjualan_ibfk_1` FOREIGN KEY (`id_member`) REFERENCES `members` (`id_member`),
  ADD CONSTRAINT `transaksi_penjualan_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
