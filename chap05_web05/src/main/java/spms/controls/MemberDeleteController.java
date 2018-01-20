package spms.controls;

import spms.dao.MySqlMemberDao;

import java.util.Map;

public class MemberDeleteController implements Controller {
    MySqlMemberDao memberDao;

    public MemberDeleteController setMemberDao(MySqlMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {

        Integer no = (Integer) model.get("no");
        memberDao.delete(no);
        return "redirect:list.do";
    }
}
