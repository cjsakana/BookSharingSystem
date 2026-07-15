package kg.us.sakanatang.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 操作工具类（精简版）
 * 包含：字符串操作、集合、排名功能
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public  boolean checkCode(String email, String rawCode) {
        String codeKey = "user:verified:";
        String code = (String) get(codeKey + email);

        rawCode=convertNonDigitsToLower(rawCode);
        code=convertNonDigitsToLower(code);

        return rawCode.equals(code);
    }
    public  String convertNonDigitsToLower(String str) {
        if (str == null) return null;
        return str.chars()
                .mapToObj(c -> Character.isDigit(c) ? (char)c : Character.toLowerCase((char)c))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    /**
     * 通用设置过期时间的方法
     * @param key 键
     * @param timeout 过期时间
     * @param unit 时间单位
     * @return 是否设置成功
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    // ==================== String 操作 ====================

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置缓存并设置过期时间
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间
     * @param unit    时间单位，如 TimeUnit.SECONDS 秒
     */
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @return 是否删除成功
     */
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    // ==================== Set 集合操作 ====================

    /**
     * 向集合添加元素
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功添加的数量
     */
    public Long addToSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 获取集合所有成员
     *
     * @param key 键
     * @return 成员集合
     */
    public Set<Object> getSetMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 判断元素是否在集合中
     *
     * @param key   键
     * @param value 值
     * @return 是否存在
     */
    public Boolean isSetMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 从集合中移除元素
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功移除的数量
     */
    public Long removeFromSet(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    // ==================== ZSet 排名操作 ====================

    /**
     * 向有序集合添加元素
     *
     * @param key   键
     * @param value 值
     * @param score 分数
     * @return 是否添加成功
     */
    public Boolean addToZSet(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 获取有序集合中元素的排名(升序)
     *
     * @param key   键
     * @param value 值
     * @return 排名(从0开始)
     */
    public Long getZSetRank(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 获取有序集合中元素的排名(降序)
     *
     * @param key   键
     * @param value 值
     * @return 排名(从0开始)
     */
    public Long getZSetReverseRank(String key, Object value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * 获取有序集合中元素的分数
     *
     * @param key   键
     * @param value 值
     * @return 分数
     */
    public Double getZSetScore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 获取有序集合指定范围内的元素(升序)
     *
     * @param key   键
     * @param start 开始位置
     * @param end   结束位置
     * @return 元素集合
     */
    public Set<Object> getZSetRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 获取有序集合指定范围内的元素(降序)
     *
     * @param key   键
     * @param start 开始位置
     * @param end   结束位置
     * @return 元素集合
     */
    public Set<Object> getZSetReverseRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 从有序集合中移除元素
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功移除的数量
     */
    public Long removeFromZSet(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    
}
