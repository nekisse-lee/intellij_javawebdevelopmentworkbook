package spms.controls;

import spms.dao.MySqlMemberDao;
import spms.vo.Member;

import java.util.Map;

public class MemberAddController implements Controller {
    MySqlMemberDao memberDao;

    public MemberAddController setMemberDao(MySqlMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }


    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if (model.get("member") == null) {
            return "/member/MemberForm.jsp";
        } else {

            Member member = (Member) model.get("member");
            memberDao.insert(member);

            return "redirect:list.do";
        }

    }
}
