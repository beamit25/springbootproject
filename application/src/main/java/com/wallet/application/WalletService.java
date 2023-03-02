package com.wallet.application;

import java.util.Collection;

public interface WalletService {
    WalletDto registerWallet(WalletDto wallet);
    WalletDto getWalletById(Integer walletId) throws WalletException;
    WalletDto updateWallet(WalletDto wallet)throws WalletException;
    WalletDto deleteWalletById(Integer walletId)throws WalletException;


    Double addFundsToWalletById(Integer walletId,Double amount)throws WalletException;
    Double withdrawFundsFromWalletById(Integer walletById,Double amount) throws WalletException;
    Boolean fundTransfer(Integer fromWalletId,Integer toWalletId,Double amount)throws WalletException;

    Collection<WalletDto> getAllWallets();
}
