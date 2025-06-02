import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Post;
import com.userDatabase.userDatabase.service.GroupService;

@Controller
public class GroupViewController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PostService postService;

    @GetMapping("/groups/{groupId}")
    public String viewGroup(@PathVariable Long groupId, Model sample) {
        Group group = groupService.getGroupById(groupId);
        List<Post> posts = postService.getPostsByGroupId(groupId);
        sample.addAttribute("group", group);
        sample.addAttribute("posts", posts);
        return "group-view"; // group-view.jsp
    }
}
