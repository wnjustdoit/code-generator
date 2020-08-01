package ${BasePackageName}${EntityPackageName};

import lombok.*;

import com.caiya.common.db.object.Page;
import com.caiya.common.db.core.annotation.*;

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