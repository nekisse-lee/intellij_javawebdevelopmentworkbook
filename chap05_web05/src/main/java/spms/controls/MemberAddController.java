package spms.controls;

import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;

import java.util.Map;

public class MemberAddController implements Controller, DataBinding{
    MySqlMemberDao memberDao;

    public MemberAddController setMemberDao(MySqlMemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "member", spms.vo.Member.class
        };

    }


    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) model.get("member");
        if (member.getEmail() == null) {  // 입력폼을 요청할때
            return "/member/MemberForm.jsp";
        } else {//회원 등록을 요청할 때
            memberDao.insert(member);
            return "redirect:list.do";
        }

    }
}
