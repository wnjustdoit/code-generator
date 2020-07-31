package ${BasePackageName}${EntityPackageName};

import lombok.*;

import com.mamaqunaer.common.db.object.Page;
import com.mamaqunaer.common.db.core.annotation.*;

/**
 * ${Remarks}
 *
 * @author ${Author}
 * @since 1.0.0, ${Date}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(name = "${TableName}")
public class ${ClassName} extends Page {

    private static final long serialVersionUID = 1L;

    ${Properties}
}