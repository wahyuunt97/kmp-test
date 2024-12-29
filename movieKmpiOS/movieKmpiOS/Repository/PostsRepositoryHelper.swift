import shared

class PostsRepositoryHelper {
    private let repository: PostRepositoryImpl

    init() {
        self.repository = PostRepositoryImpl(api: KtorClient())
    }

    func fetchPosts(completion: @escaping (Result<[Post], Error>) -> Void) {
        DispatchQueue.main.async {
            Task {
                do {
                    // Fetch the result
                    let result = try await self.repository.getPosts()

                    // Unwrap the optional first
                    if let unwrappedResult = result {
                        // Try casting to Success<[Post]>
                        if let success = unwrappedResult as? Success<[Post]> {
                            // Access the posts
                            for post in success.value {
                                print("Post ID: \(post.id), Title: \(post.title), Body: \(post.body)")
                            }
                        } else {
                            print("Failed to cast unwrappedResult to Success<[Post]>. Raw data:")
                            printRawData(unwrappedResult, label: "Raw Unwrapped Result")
                        }
                    } else {
                        print("Result is nil.")
                    }
                } catch {
                    print("Error fetching posts: \(error)")
                }
            }
        }
    }
}

func printRawData(_ data: Any, label: String) {
    print("\(label):")
    let stringRepresentation = String(describing: data)

    // Truncate if data is too large
    let maxLength = 1000
    if stringRepresentation.count > maxLength {
        let truncated = stringRepresentation.prefix(maxLength) + "...\n[TRUNCATED]"
        print(truncated)
    } else {
        print(stringRepresentation)
    }
}

struct Success<T> {
    let value: T
}