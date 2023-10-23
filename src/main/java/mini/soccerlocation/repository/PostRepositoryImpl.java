package mini.soccerlocation.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mini.soccerlocation.domain.Post;
import mini.soccerlocation.request.PostSearch;
import org.springframework.util.StringUtils;

import java.util.List;

import static mini.soccerlocation.domain.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public List<Post> getList(PostSearch postSearch) {
        String title = postSearch.getTitle();
        String content = postSearch.getContent();

        return query.select(post)
                .from(post)
                .where(likePostTitle(title), likePostContent(content))
                .fetch();
    }

    private BooleanExpression likePostTitle(String title) {
        if (StringUtils.hasText(title)) {
            return post.title.like("%" + title + "%");
        }
        return null;
    }

    private BooleanExpression likePostContent(String content) {
        if (StringUtils.hasText(content)) {
            return post.content.like("%" + content + "%");
        }
        return null;
    }


}
