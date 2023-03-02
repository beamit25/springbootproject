package com.wallet.application;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository <WalletDto,Integer> {

//    WalletDto createWallet(WalletDto newWallet);
//
//    WalletDto getWalletById(Integer  walletId);
//    WalletDto updateWallet(WalletDto wallet);
//    WalletDto deleteWalletById(Integer walletId);

}
