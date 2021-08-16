package com.ctsi.test.repository;

import com.ctsi.test.domain.Gkc;
import com.ctsi.test.domain.GkcExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

/**
 * @author ctsi-biyi-generator
*/
@Mapper
public interface GkcRepository extends com.ctsi.ssdc.repository.BaseRepository<Gkc, Integer, GkcExample> {
    /**
     *
     * @mbg.generated
     */
    @SelectProvider(type=GkcSqlProvider.class, method="countByExample")
    long countByExample(GkcExample example);

    /**
     *
     * @mbg.generated
     */
    @DeleteProvider(type=GkcSqlProvider.class, method="deleteByExample")
    int deleteByExample(GkcExample example);

    /**
     *
     * @mbg.generated
     */
    @Delete({
        "delete from gkc",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    @Insert({
        "insert into gkc (name, age)",
        "values (#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insert(Gkc record);

    /**
     *
     * @mbg.generated
     */
    @InsertProvider(type=GkcSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insertSelective(Gkc record);

    /**
     *
     * @mbg.generated
     */
    @SelectProvider(type=GkcSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER)
    })
    List<Gkc> selectByExample(GkcExample example);

    /**
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, name, age",
        "from gkc",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER)
    })
    Gkc selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    @UpdateProvider(type=GkcSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Gkc record, @Param("example") GkcExample example);

    /**
     *
     * @mbg.generated
     */
    @UpdateProvider(type=GkcSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Gkc record, @Param("example") GkcExample example);

    /**
     *
     * @mbg.generated
     */
    @UpdateProvider(type=GkcSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Gkc record);

    /**
     *
     * @mbg.generated
     */
    @Update({
        "update gkc",
        "set name = #{name,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Gkc record);
}