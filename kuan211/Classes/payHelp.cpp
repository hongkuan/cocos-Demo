#include "payHelp.h"


PayHelp::PayHelp()
{
	_payQuantity = new char[1];
	_payPrice = new char[1];
	_payId = new char[1];
}

PayHelp::~PayHelp()
{
	CC_SAFE_DELETE(_payQuantity);
	CC_SAFE_DELETE(_payPrice);
	CC_SAFE_DELETE(_payId);
}

void PayHelp::setPayQuantity(const char* _sPayQuantity)
{
	//_payQuantity = _sPayQuantity;
	CC_SAFE_DELETE(_payQuantity);
	_payQuantity = new char[strlen(_sPayQuantity)+1];
    strcpy(_payQuantity,_sPayQuantity);
    _payQuantity[strlen(_sPayQuantity)] = '\0';
	CCLOG("ShopShowUi end");
	CCLOG("_sPayQuantity = %s \n",_payQuantity);
};
	
void PayHelp::setPayPrice(const char* _spayPrice)
{
	//_payPrice = _spayPrice;
	CC_SAFE_DELETE(_payPrice);
	_payPrice = new char[strlen(_spayPrice)+1];
    strcpy(_payPrice,_spayPrice);
    _payPrice[strlen(_spayPrice)] = '\0';
		CCLOG("_spayPrice = %s \n",_payPrice);
		
};

void PayHelp::setPayId(const char* _spayId)
{
	//_payId = _spayId;
	CC_SAFE_DELETE(_payId);
	_payId = new char[strlen(_spayId)+1];
    strcpy(_payId,_spayId);
    _payId[strlen(_spayId)] = '\0';
	CCLOG("_spayId = %s \n", _payId);
};
