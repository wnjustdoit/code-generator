package ${BasePackageName}${ControllerPackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};
import ${BasePackageName}${ServicePackageName}.${ClassName}Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/${EntityName}")
public class ${ClassName}Controller {

    private static final Logger logger = LoggerFactory.getLogger(${ClassName}Controller.class);

    @Resource
    private ${ClassName}Service ${EntityName}Service;


    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public Object list() {
        List<${ClassName}> ${EntityName}s = ${EntityName}Service.findAllList();
        return ${EntityName}s;
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        ${ClassName} ${EntityName} = ${EntityName}Service.get(id);
        return ${EntityName};
    }

    /**
     * 新增
     */
    @PostMapping
    public String insert(@RequestBody ${ClassName} ${EntityName}) {
        if (${EntityName}Service.insert(${EntityName}) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public String insertBatch(@RequestBody List<${ClassName}> ${EntityName}s) {
        if (${EntityName}Service.insertBatch(${EntityName}s) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody ${ClassName} ${EntityName}) {
        if (${EntityName}Service.update(${EntityName}) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody ${ClassName} ${EntityName}) {
        if (${EntityName}Service.delete(${EntityName}) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

}
