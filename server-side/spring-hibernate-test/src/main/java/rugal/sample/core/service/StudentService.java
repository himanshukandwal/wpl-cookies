package rugal.sample.core.service;

import edu.utdallas.wpl.dao.StudentDAO;
import rugal.sample.core.entity.Student;

/**
 *
 * @author Rugal Bernstein
 */
public interface StudentService extends BaseService<StudentDAO>
{

    Student update(Student bean);
}
