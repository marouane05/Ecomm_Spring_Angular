package com.ecom.service;

import javax.crypto.SecretKey;


import io.jsonwebtoken.SigningKeyResolver;


public interface AuthSigningKeyResolver extends SigningKeyResolver{

	SecretKey getSercretkey();
}
