@Controller
public class GroupViewController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PostService postService;

    @GetMapping("/groups/{groupId}")
    public String viewGroup(@PathVariable Long groupId, Model model) {
        Group group = groupService.getGroupById(groupId);
        List<Post> posts = postService.getPostsByGroupId(groupId);
        model.addAttribute("group", group);
        model.addAttribute("posts", posts);
        return "group-view"; // group-view.jsp
    }
}
