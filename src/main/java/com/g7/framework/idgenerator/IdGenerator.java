package com.g7.framework.idgenerator;

import com.g7.framework.idgenerator.utils.UidContext;

/**
 * @author dreamyao
 */
public interface IdGenerator {

    long next();

    UidContext parseUid(long uid);
}
