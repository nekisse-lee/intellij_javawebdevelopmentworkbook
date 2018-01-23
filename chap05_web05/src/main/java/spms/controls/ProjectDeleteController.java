package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;

import java.sql.Connection;
import java.util.Map;

@Component("/project/delete.do")
public class ProjectDeleteController implements Controller, DataBinding{
    ProjectDao projectDao;

    public ProjectDeleteController setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "no", Integer.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Integer no = (Integer) model.get("no");
        projectDao.delete(no);
        return null;
    }
}
