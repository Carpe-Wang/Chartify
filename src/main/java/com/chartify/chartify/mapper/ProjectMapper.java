package com.chartify.chartify.mapper;

import com.chartify.chartify.entity.ProjectData;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface ProjectMapper {

    @InsertProvider(type = ProjectSqlProvider.class, method = "createProject")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createProject(ProjectData project);

    @Select("SELECT * FROM project WHERE id = #{id}")
    @Results(id = "projectResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "base64Image", column = "base64_image"),
            @Result(property = "createdTime", column = "created_time"),
            @Result(property = "updatedTime", column = "updated_time")
    })
    ProjectData getProjectById(int id);

    @UpdateProvider(type = ProjectSqlProvider.class, method = "updateProject")
    int updateProject(ProjectData project);

    @Delete("DELETE FROM project WHERE id = #{id}")
    int deleteProject(int id);

    class ProjectSqlProvider {

        public String createProject(final ProjectData project) {
            return new SQL() {{
                INSERT_INTO("project");
                VALUES("name", "#{name}");
                VALUES("description", "#{description}");
                VALUES("user_id", "#{userId}");
                VALUES("base64_image", "#{base64Image}");
                VALUES("created_time", "CURRENT_TIMESTAMP");
                VALUES("updated_time", "CURRENT_TIMESTAMP");
            }}.toString();
        }

        public String updateProject(final ProjectData project) {
            return new SQL() {{
                UPDATE("project");
                if (project.getName() != null) {
                    SET("name = #{name}");
                }
                if (project.getDescription() != null) {
                    SET("description = #{description}");
                }
                if (project.getUserId() != null) {
                    SET("user_id = #{userId}");
                }
                if (project.getBase64Image() != null) {
                    SET("base64_image = #{base64Image}");
                }
                SET("updated_time = CURRENT_TIMESTAMP");
                WHERE("name = #{name}");
            }}.toString();
        }
    }
}
