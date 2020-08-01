package ${BasePackageName}${ControllerPackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};
import org.springframework.web.bind.annotation.*;

import com.caiya.common.db.core.JdbcOperator;
import com.caiya.common.db.core.sql.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * ${ClassName}Controller.
 *
 * @author ${Author}
 * @since 1.0.0, ${Date}
 */
@RestController
@RequestMapping("/${ClassURI}")
public class ${ClassName}Controller {

    private static final Logger logger = LoggerFactory.getLogger(${ClassName}Controller.class);

    @Resource
    private JdbcOperator jdbcOperator;


    @GetMapping
    public Object queryForList(${ClassName} ${EntityName}) {
        List<Map> list = jdbcOperator.queryForListByConditions(${EntityName}, Order.newOrder("id", Direction.DESC));
        int total = jdbcOperator.count(${EntityName});
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        return result;
    }

    @GetMapping("/{id}")
    public Object queryForObject(@PathVariable Long id) {
        Map<String, Object> result = jdbcOperator.queryForObjectByPrimaryKey(id, ${ClassName}.class);
        return result;
    }

    @PostMapping
    public Object save(${ClassName} ${EntityName}) {
        int result = jdbcOperator.save(${EntityName});
        return result;
    }

    @PutMapping
    public Object update(${ClassName} ${EntityName}) {
        int result = jdbcOperator.updateByPrimaryKey(${EntityName});
        return result;
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        int result = jdbcOperator.deleteByPrimaryKey(id, ${ClassName}.class);
        return result;
    }

}
