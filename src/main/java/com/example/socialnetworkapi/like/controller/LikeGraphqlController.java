package com.example.socialnetworkapi.like.controller;

import com.example.socialnetworkapi.comment.node.Comment;
import com.example.socialnetworkapi.comment.repository.CommentRepository;
import com.example.socialnetworkapi.like.dto.CreateLikeInput;
import com.example.socialnetworkapi.like.dto.LikePage;
import com.example.socialnetworkapi.like.node.Like;
import com.example.socialnetworkapi.like.repository.LikeRepository;
import com.example.socialnetworkapi.post.node.Post;
import com.example.socialnetworkapi.post.repository.PostRepository;
import com.example.socialnetworkapi.user.node.User;
import com.example.socialnetworkapi.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LikeGraphqlController {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public LikeGraphqlController(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository,
                                 CommentRepository commentRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @QueryMapping
    public List<Like> likeList() {
        return likeRepository.findAll();
    }

    @QueryMapping
    public List<Like> getLikeByPostId(@Argument Long postId) {
        return likeRepository.findByPostId(postId);
    }

    @QueryMapping
    public LikePage allLikes(int page, int size) {
        Page<Like> likePage = likeRepository.findAll(PageRequest.of(page, size));
        return LikePage.builder()
                .likes(likePage.getContent())
                .totalCount((int) likePage.getTotalElements())
                .hasNextPage(likePage.hasNext())
                .hasPreviousPage(likePage.hasPrevious())
                .build();
    }

    @MutationMapping
    public Like createLike(@Argument("input") CreateLikeInput createLikeInput) {
        Like like = new Like();

        User user = userRepository.findById(createLikeInput.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        like.setUser(user);
        if (createLikeInput.getPostId() != null) {
            Post post = postRepository.findById(createLikeInput.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));
            like.setPost(post);
        }
        if (createLikeInput.getCommentId() != null) {
            Comment comment = commentRepository.findById(createLikeInput.getCommentId()).orElseThrow(() -> new RuntimeException("Comment not found"));
            like.setComment(comment);
        }
        return likeRepository.save(like);
    }
}
