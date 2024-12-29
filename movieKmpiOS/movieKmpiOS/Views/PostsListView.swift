import SwiftUI
import shared

struct PostsListView: View {
    @StateObject private var viewModel = PostsViewModel()

    var body: some View {
        NavigationView {
            Group {
                if viewModel.isLoading {
                    ProgressView()
                } else if let error = viewModel.error {
                    Text("Error: \(error.localizedDescription)")
                        .foregroundColor(.red)
                } else {
                    List(viewModel.posts, id: \.id) { post in
                        PostRow(post: post)
                    }
                }
            }
            .navigationTitle("Posts")
        }
        .onAppear {
            viewModel.fetchPosts()
        }
    }
}

struct PostRow: View {
    let post: Post

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(post.title)
                .font(.headline)
            Text(post.body)
                .font(.subheadline)
                .foregroundColor(.secondary)
        }
        .padding(.vertical, 8)
    }
}
