package com.wallet.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService{
    @Autowired
    private WalletRepository walletRepository;
    @Override
    public WalletDto registerWallet(WalletDto wallet){
        return this.walletRepository.save(wallet);
    }

    @Override
    public WalletDto getWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> foundWallet = this.walletRepository.findById(walletId);
        if(foundWallet == null)
            throw new WalletException("Employee Id does not exists.");

        return foundWallet.get();
    }

    @Override
    public WalletDto updateWallet(WalletDto walletDto) throws WalletException{
        if(this.walletRepository.findById(walletDto.getId()) == null){
            throw new WalletException("No such Wallet exists");
        }
        return this.walletRepository.save(walletDto);
    }
    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException{
        Optional<WalletDto> foundWallet = this.walletRepository.findById(walletId);
        if(foundWallet== null){
            throw new WalletException("No such Wallet Exists");
        }
        WalletDto wallet = foundWallet.get();
         this.walletRepository.delete(wallet);
        return wallet;
    }
    @Override
    public Double addFundsToWalletById(Integer walletId, Double amountToBeAdded) throws WalletException{
        Optional<WalletDto> fundwallet = this.walletRepository.findById(walletId);
        if(fundwallet == null){
            throw new WalletException("No such Wallet Exists");
        }else if(amountToBeAdded<=0){
            throw new WalletException("The amount to be added should be greater than 0");
        }
        Double newBalance = fundwallet.get().getBalance() + amountToBeAdded;
        fundwallet.get().setBalance(newBalance);
        this.walletRepository.save(fundwallet.get());
        return newBalance;
    }
    //
    @Override
    public Double withdrawFundsFromWalletById(Integer walletId, Double amount) throws WalletException{
        Optional<WalletDto> tempwallet = this.walletRepository.findById(walletId);
        if(tempwallet == null){
            throw new WalletException("No such Wallet Exists");
        }else if(amount >= tempwallet.get().getBalance()){
            throw new WalletException("Insufficient balance. Current Balance: " + tempwallet.get().getBalance() );
        }
        Double withdrawAmount = tempwallet.get().getBalance();
        withdrawAmount -= amount;
        tempwallet.get().setBalance(withdrawAmount);
        this.walletRepository.save(tempwallet.get());
        return withdrawAmount;
    }
    //
    @Override
    public Boolean fundTransfer(Integer fromWalletId, Integer toWalletId, Double amountToTransfer) throws WalletException{
        Optional<WalletDto> fromWallet = this.walletRepository.findById(fromWalletId);
        Optional<WalletDto> toWallet = this.walletRepository.findById(toWalletId);

        if(fromWallet == null){
            throw new WalletException("From Wallet not exist");
        }else if(toWallet == null){
            throw new WalletException("To wallet not exist");
        } else if(amountToTransfer >= fromWallet.get().getBalance()){
            throw new WalletException("Insufficient Balance. Current Balance: " + fromWallet.get().getBalance());
        }

        fromWallet.get().setBalance(fromWallet.get().getBalance()-amountToTransfer);
        toWallet.get().setBalance(toWallet.get().getBalance()+amountToTransfer);
        this.walletRepository.save(fromWallet.get());
        this.walletRepository.save(toWallet.get());
        return true;
    }

    @Override
    public Collection<WalletDto> getAllWallets() {
        return this.walletRepository.findAll();
    }

//    @Override
//    public Double addFundsToWalletById(Integer walletId, Double amount) throws WalletException {
//        return null;
//    }
//
//    @Override
//    public Double withdrawFundsFromWalletById(Integer walletById, Double amount) throws WalletException {
//        return null;
//    }
//
//    @Override
//    public Boolean fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException {
//        return null;
//    }
//
//    @Override
//    public List<WalletDto> getAllWallets() {
//        return null;
//    }
}
