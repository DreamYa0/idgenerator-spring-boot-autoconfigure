package com.g7.framework.idgenerator;

import com.g7.framework.idgenerator.utils.UidContext;

/**
 * @author dreamyao
 */
public class SnowflakeIdGenerator implements IdGenerator {

    private final UidGenerator uidGenerator;

    public SnowflakeIdGenerator(UidGenerator uidGenerator) {
        this.uidGenerator = uidGenerator;
    }

    /**
     * 废弃，请使用 {@link DefaultUidGenerator getUid} 方法
     * 示例：
     *     @Autowired
     *     private UidGenerator uidGenerator
     * @return 全局唯一ID
     */
    @Override
    @Deprecated
    public long next() {
        return uidGenerator.getUid();
    }

    /**
     * 废弃，请使用 {@link DefaultUidGenerator parseUid} 方法
     * 示例：
     *     @Autowired
     *     private UidGenerator uidGenerator
     * @return
     */
    @Override
    @Deprecated
    public UidContext parseUid(long uid) {
        return uidGenerator.parseUid(uid);
    }
}
