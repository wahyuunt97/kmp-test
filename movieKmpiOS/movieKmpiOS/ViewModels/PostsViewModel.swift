import SwiftUI
import shared

@MainActor
class PostsViewModel: ObservableObject {
    @Published var posts: [Post] = []
    @Published var isLoading = false
    @Published var error: Error?

    private let repository = PostsRepositoryHelper()

    func fetchPosts() {
        isLoading = true
        repository.fetchPosts { [weak self] result in
            DispatchQueue.main.async {
                switch result {
                case .success(let fetchedPosts):
                    self?.posts = fetchedPosts
                    self?.isLoading = false
                case .failure(let error):
                    self?.error = error
                    self?.isLoading = false
                }
            }
        }
    }
}
