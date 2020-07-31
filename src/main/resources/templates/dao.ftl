package ${BasePackageName}${DaoPackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ${Author}
 * @since 1.0.0, ${Date}
 */
@Mapper
@Component
public interface ${ClassName}Dao {

    public ${ClassName} get(String id);

    public List<${ClassName}> findList(${ClassName} ${EntityName});

    public List<${ClassName}> findAllList();

    public int insert(${ClassName} ${EntityName});

    public int insertBatch(List<${ClassName}> ${EntityName}s);

    public int update(${ClassName} ${EntityName});

    public int delete(${ClassName} ${EntityName});

}